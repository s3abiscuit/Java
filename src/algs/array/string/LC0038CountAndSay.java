//给定一个正整数 n ，输出外观数列的第 n 项。
//
// 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
//
// 你可以将其视作是由递归公式定义的数字字符串序列：
//
//
// countAndSay(1) = "1"
// countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
//
//
// 前五项如下：
//
//
//1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
//第一项是数字 1
//描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
//描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
//描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
//描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
//
//
// 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成
//一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
//
// 例如，数字字符串 "3322251" 的描述如下图：
//
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 1
//输出："1"
//解释：这是一个基本样例。
//
//
// 示例 2：
//
//
//输入：n = 4
//输出："1211"
//解释：
//countAndSay(1) = "1"
//countAndSay(2) = 读 "1" = 一 个 1 = "11"
//countAndSay(3) = 读 "11" = 二 个 1 = "21"
//countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
//
//
//
//
// 提示：
//
//
// 1 <= n <= 30
//
// Related Topics 字符串 👍 751 👎 0
package algs.array.string;

public class LC0038CountAndSay {
    public static void main(String[] args) {

    }

    public String countAndSay(int n) {
        // 递归出口
        if(n==1){
            return "1";
        }
        // 假设我们获得上一次的结果为 s1 = 112213
        String s1 = countAndSay(n - 1);
        // 定义结果
        StringBuilder result = new StringBuilder();
        // 对s1遍历处理获取值
        char local = s1.charAt(0);
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            // 设定计数器 计算同一个数字出现的次数 count
            if(s1.charAt(i) == local){
                count++;
            }else {
                // 不符合，记录下
                result.append(count);
                result.append(local);
                count = 1;
                local = s1.charAt(i);
            }
        }
        result.append(count);
        result.append(local);
        return result.toString();
    }

    private StringBuffer s1;
    {
        s1 = new StringBuffer();
        s1.append('1');
    }

    public String countAndSay1(int n) {
        for(int i = 1; i < n; i++){
            StringBuffer s2 = product(s1);
            s1 = s2;
        }

        return s1.toString();

    }

    public StringBuffer product(StringBuffer s1){
        StringBuffer s2 = new StringBuffer();

        int count = 0;
        char local = s1.charAt(0);
        for(int i = 0; i < s1.length();){
            if(local == s1.charAt(i)){
                count++;
                i++;
            }else{
                s2.append(count).append(local);
                local = s1.charAt(i);  // 更新local
                count = 0;  // 重新开始计数
            }
        }

        // 退出循环的时候，最后还有数字没有加入
        return s2.append(count).append(local);
    }

}
