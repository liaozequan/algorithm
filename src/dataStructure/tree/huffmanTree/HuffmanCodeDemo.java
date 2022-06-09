package dataStructure.tree.huffmanTree;

import java.util.*;

/**
 * 哈夫曼编码压缩文件，解压文件
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        String content = "i like java and do you like java";
        byte[] code = huffmanZip(content);
        System.out.println(Arrays.toString(code));
        byte[] sourceByte = decode(huffmanCodes, code);
        for(int i=0; i<sourceByte.length; i++){
            System.out.print((char) sourceByte[i]);
        }
    }


    /**
     * 封装哈夫曼编码压缩字符串的所有方法
     * @param content   待压缩的字符串
     * @return  字符串对应的哈夫曼编码数组
     */
    public static byte[] huffmanZip(String content){
        byte[] contentBytes = content.getBytes();//将content所有字符转换成ascii码存放到byte[]
        //生成哈夫曼节点数组
        List<HuffmanCodeNode> nodeList = getNodeList(contentBytes);
        //生成哈夫曼树
        HuffmanCodeNode root = createHuffmanCodeTree(nodeList);
        //得到所有字符的哈夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(root, "", stringBuilder);
        return zip(contentBytes, huffmanCodes);
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的哈夫曼编码表，替换byte[]数组所有元素
     * @param bytes 字符串对应的byte[]数组
     * @param huffmanCodes 哈夫曼编码表
     * @return返回一个哈夫曼编码压缩后的byte[]
     * "i like java and do you like java"转换成huffmanCodes==>"1111001001111110000110000111110101111000110111000101...."
     * 接下来将8位做成1字节，实现压缩
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将stringBuilder（即整个字符串转换成哈夫曼编码）按8位1字节压缩
        int len;    //转换成字节后的字符串长度
        if(stringBuilder.length() %8 == 0){
            len = stringBuilder.length() /8;
        }else{
            //长度不够8整除，则填0（通过将长度加1）
            len = stringBuilder.length() /8 +1;
        }
        //创建 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;    //huffmanCodeBytes数组的下标
        for(int i=0; i<stringBuilder.length(); i+=8){   //一个字节8bite步长为8
            String strByte;
            if(i+8 > stringBuilder.length()){
                //如果剩余的数组元素不够取8位，则直接取到数组的最后一位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i+8);
            }
            //将8个位的数据转换成一个字节
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);  //二进制的8位转换成一字节
            index++;
        }
        return huffmanCodeBytes;
    }



    /**
     * 接收原字符串转换成的ascii码数组，返回对应字符与出现次数的节点数组
     */
    private static List<HuffmanCodeNode> getNodeList(byte[] bytes){
        ArrayList<HuffmanCodeNode> nodeList = new ArrayList<>();

        //统计每一个字符出现的次数，map实现
        Map<Byte, Integer> map = new HashMap<>();
        for(byte b : bytes){
                Integer count = map.get(b);
                if(count == null){
                    map.put(b,1);
                }else{
                    map.put(b,count+1);
                }

        }

        //把map中每个键值对元素转换成HuffmanCodeNode，放入nodeList中
        for(Map.Entry<Byte, Integer> entry : map.entrySet()){
            nodeList.add(new HuffmanCodeNode(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }

    /**
     * 接收无需哈夫曼节点数组，返回哈夫曼树
     */
    private static HuffmanCodeNode createHuffmanCodeTree(List<HuffmanCodeNode> nodeList){
        while (nodeList.size() > 1){
            Collections.sort(nodeList);
            HuffmanCodeNode leftNode = nodeList.get(0);
            HuffmanCodeNode rightNode = nodeList.get(1);
            HuffmanCodeNode parent = new HuffmanCodeNode(null, leftNode.weight+ rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }


    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 生成哈夫曼树对应的哈夫曼编码表
     * 存放在map中，如<97,100>即a对应的哈夫曼编码为100
     * 在生成哈夫曼编码时，需要不停拼接路径，，定义stringbuilder存储某个叶子节点路径
     * @param node  传入哈夫曼树节点
     * @param code  路径：左子节点是0， 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static Map<Byte, String> getCodes(HuffmanCodeNode node ,String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            //判断node是叶子节点还是非叶子节点
            if(node.data==null){
                //如果是非叶子节点，递归处理，向左子节点递归,哈夫曼编码加0
                getCodes(node.left, "0", stringBuilder2);
                //向右子节点递归,哈夫曼编码加1
                getCodes(node.right, "1", stringBuilder2);
            }else{
                //如果是叶子节点，就停止递归，将哈夫曼编码放入map（哈夫曼编码表中）
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
        return huffmanCodes;
    }

    private static void preOrder(HuffmanCodeNode node){
        if(node ==null){
            return;
        }
        System.out.println(node.toString());
        preOrder(node.left);
        preOrder(node.right);
    }

    /** 数据解压
     * 将哈夫曼编码压缩后的<字节>数据[-14, 126, 24, 125, 120, -36, 81, 104, 74, -23, 63, 12, 62, 94]
     * 转换为位数据11110010011111100001100001111101011110....
     * 在对应哈夫曼编码表还原为"i like java and do you like java"
     */

    /**该方法将一个字节的数据转换成8为字符串
     * @param flag  标志是否需要补高位
     */
    private static String byteToBitString(boolean flag, byte b){
        int temp = b; //将字节byte型强制转换为int型
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    /**
     * 对压缩数据解压
     * @param huffmanCodes  哈夫曼编码表map<>
     * @param huffmanBytes  哈夫曼编码后得到的字节数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<huffmanBytes.length; i++){
            boolean flag = (i != huffmanBytes.length-1);    //如果不是最后一个字节，flag=true，，说明需要补齐高位
            stringBuilder.append(byteToBitString(flag, huffmanBytes[i]));


        }
        //循环结束后的stringBuilder=111100100111111000011000011111010111100011011100010...
        //将哈夫曼编码表的key，value进行调换 即<97,1100> ==> <1100,97>
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        //扫描stringBuilder=111100100111111...，当遇到符合哈夫曼编码的一段，就翻译成ascii（通过哈夫曼编码表）
        for(int i=0; i<stringBuilder.length();){
            int count = 1;  //计算截取stringBuilder的长度
            boolean flag = true;
            Byte b = null;
            while (flag){
                //取出stringBuilder中的一个字符
                String key = stringBuilder.substring(i, i+count);
                b = map.get(key);
                if(b != null){
                    flag = false;//已经找到可以对应哈夫曼编码表的一定字符，停止循环
                }else{
                    count++;
                }
            }
            list.add(b);
            i+=count;//i移动到截取字符串的最后一个位置
        }
        // 把list数组中的数据放到byte[]中并返回
        byte[] b = new byte[list.size()];
        for(int i=0; i<b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }
}

//哈夫曼树节点，存放字符数据和出现次数
class HuffmanCodeNode implements Comparable<HuffmanCodeNode>{
    Byte data;  //存放数据本身，如"a"==>97
    int weight; //字符在文件中出现的次数
    HuffmanCodeNode left;
    HuffmanCodeNode right;

    public HuffmanCodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(HuffmanCodeNode o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanCodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
