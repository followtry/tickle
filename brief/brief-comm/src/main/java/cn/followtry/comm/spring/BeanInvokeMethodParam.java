package cn.followtry.comm.spring;

import cn.followtry.comm.spring.converter.TypeConverter;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: haozhongweng
 * @create: 2018/6/28 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="springBean调用对象value",description="springBean调用对象des")
public class BeanInvokeMethodParam {

    private static final String PT_STR = "^\\s*([a-zA-Z0-9_$\\.]+)(<(.+)>)?";

    @ApiModelProperty(value="参数类型(List<Integer>, 有多态时必须有)")
    private String type;
    @ApiModelProperty(value="参数值(数值/true/false/String/json)")
    private String value;
    @ApiModelProperty(value="内部值,不用填")
    private transient String rawTypeName;
    @ApiModelProperty(value="内部值,不用填")
    private transient String parameterizedTypeName;

    public BeanInvokeMethodParam(String ivalue){
        this.value = ivalue;
    }

    public BeanInvokeMethodParam(String itype, String ivalue){
        this(ivalue);
        this.type = itype;
    }

    /**
     * 获得当前的头类型和其余
     */
    public void matchTypeName(){
        if (Objects.nonNull(rawTypeName)) {
            // 已初始过
            return ;
        }
        Pattern r = Pattern.compile(PT_STR);
        Matcher m = r.matcher(type);
        if (m.find()) {
            int cntGroup = m.groupCount();
            if (cntGroup == 3) {
                rawTypeName = m.group(1);
                String g2 = m.group(2);
                if (Objects.nonNull(g2)){
                    g2 = g2.substring(1, g2.length()-1);
                }
                parameterizedTypeName = g2;
            } else if(cntGroup == 2){
                rawTypeName = m.group(1);
                parameterizedTypeName = null;
            } else {
                rawTypeName = parameterizedTypeName = null;
            }
        } else {
            rawTypeName = parameterizedTypeName = null;
        }
    }

    /**
     * 输入泛型字符串,解析得到对应的ParameterizedType或Class
     * 如Integer、List<Integer>、Map<List<String>,Map<String, List<Integer>>>
     * @param
     * @return
     * @throws ClassNotFoundException
     */
    public Pair<ParameterizedType, Class> fetchParameterizedType() throws ClassNotFoundException {
        if (StringUtils.isBlank(type)) {
            return null;
        }
        type = type.replace(" ", "");
        Triple<Integer, ParameterizedType, Class> res = matchParameterizedType(type.toCharArray(), 0, type.length()-1);

        return Pair.of(res.getMiddle(), res.getRight());
    }

    public Triple<Integer, ParameterizedType, Class> matchParameterizedType(char[] typeStr, int beg, int end) throws ClassNotFoundException {

        int iBeg = beg;
        StringBuilder rawTypeStr = new StringBuilder();
        List<Type> actualTypeArguments = Lists.newArrayList();

        // 找rawType
        boolean noNest = true;

        while(iBeg <= end){

            char charCur = typeStr[iBeg];
            if (charCur == '<') {
                noNest = false;
                // 从下一个位置开始递归匹配
                Triple<Integer, ParameterizedType, Class> childRes = matchParameterizedType(typeStr, iBeg +1, end);

                ParameterizedType childType = childRes.getMiddle();
                Class<?> childClass = childRes.getRight();
                actualTypeArguments.add(Objects.isNull(childType)?childClass:childType);
                // 检索位置重置
                iBeg = childRes.getLeft() + 1;
                continue;

            } else if(charCur == ','){
                // 可能是结束标志, 也可能是多个泛型参数的分割
                if (noNest) {
                    iBeg -- ;
                    break;

                } else {
                    // 从下一个位置开始递归匹配
                    Triple<Integer, ParameterizedType, Class> childRes = matchParameterizedType(typeStr, iBeg +1, end);

                    ParameterizedType childType = childRes.getMiddle();
                    Class<?> childClass = childRes.getRight();
                    actualTypeArguments.add(Objects.isNull(childType)?childClass:childType);
                    // 检索位置重置
                    iBeg = childRes.getLeft() + 1;
                    continue;
                }
            } else if (typeStr[iBeg] == '>'){
                // 结束标志, 可能是本身的，也可能是上一层的
                if (noNest) {
                    iBeg -- ;
                }
                break;
            } else {
                rawTypeStr.append(typeStr[iBeg++]);
            }
        }
        if (noNest){
            // 没有嵌套
            Class<?> typeClass = TypeConverter.me().fetchTypeClass(rawTypeStr.toString());
            return Triple.of(iBeg, null, typeClass);
        }

        Class<?> rawClass = TypeConverter.me().fetchTypeClass(rawTypeStr.toString());
        ParameterizedTypeImpl type = ParameterizedTypeImpl.make(rawClass, actualTypeArguments.stream().toArray(Type[]::new), null);
        return Triple.of(iBeg, type, null);
    }

}
