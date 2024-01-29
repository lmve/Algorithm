/*
 * 2024.1.26
 * 认识复杂度、对数器、二分法、异或运算
 *
 */

package data_struct;
class Code1_SelectionSort{
    // 选择排序
    public static void selectionSort(int[] arr){
        if (arr.length < 2 || arr == null){
            return;
        }
        // 0 ~ N-1
        // 1 ~ N-1
        // 2 ~ N-1
        for (int i=0; i< arr.length; i++){
            int min_index = i;
            for (int j=i+1; j< arr.length; j++){
                min_index = arr[j] < arr[min_index] ? j : min_index;
            }
            swap(arr,i,min_index);
        }
    }
    // 冒泡排序
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i= arr.length-1; i>0; i--){
            for (int j=0; j<i; j++){
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    // 插入排序 (数据会影响算法的性能,会破坏数据的稳定性)
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i=1; i<arr.length; i++){ //做到 i ~ N 上有序
            for (int j=i-1; j>=0 && arr[j] > arr[j+1]; j--){
                swap(arr,j,j+1);
            }
        }
    }
    // 交换 -----> 异或运算( i & j 是同一位置时就会出问题)
    public static void swap(int[] arr,int i, int j){
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    public static void print(int[] arr){
        for (int i=0; i< arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    // 随机数据产生器
    public static int[] generateRandomArray(int maxSize,int maxValue){  //控制数组大小以及数据值
        // Math.random()             [0,1) 随机值
        // Math.random() * N         [0,N) 随机值
        // (int) Math.random() * N   [0,N-1]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; //大小随机的数组
        for (int i=0; i<arr.length;i++){
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    /*
     * 二分
     * 1) 在一个有序的数组中，查找某个数
     * 2) 在一个有序的数组中，找 >= 某个数最左侧的位置
     * 3) 在一个有序的数组中，找 <= 某个数最右侧的位置
     * 4) 局部最小值问题
     */

    // 利用二分法查找某个数
    public static boolean exist(int[] sortedArr, int num){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        //在 L 。。 R 的范围上不断二分
        while(L < R){ //在 L 到 R 的范围上
            // mid = (L + R) / 2;
            // L + R 可能会溢出
            // mid = L + (R - L) / 2; equ mid = L + ((R - L) >> 1);
            // n * 2 + 1; equ ((n << 1) | 1)
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num){
                return true;
            } else if (sortedArr[mid] > num){ //右侧可以排除了
                R = mid - 1; //更新 R
            } else { //左侧可以排除
                L = mid + 1; //更新 L
            }
        }
        // L = R 时需要判断最后一个数
        return sortedArr[L] == num;
    }

    // 利用二分法 找到 >= value 的最左侧的位置
    public static int nearestIndex(int[] sortedArr, int value){
        // 异常输入返回 -1；
        if (sortedArr == null || sortedArr.length == 1){
            return -1;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int index = -1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= value){
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
    /*
     * 1） 左边界 0 位置小于 1 位置
     * 2） 右边界 N 位置小于 N-1 位置
     * 3） 中间 i 位置比 i-1、i+1 位置都小
     * 例 ： 有一个无序数组， 任意相邻两个值不相等 查找一个局部最小
     *     看局部变化趋势 据此二分（找到排他性)
     */
    public static int getLessIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]){ // 第一种局部最小
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]){  //第二种局部最小
            return arr.length - 1;
        }
        // 第三种局部最小
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while(L < R){
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid -1]){ //弃右
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) { //弃左
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }
    /*
     * 异或运算 (无进位加法)
     * 0 ^ N == N    N ^ N == 0
     * 异或运算满足交换率与结合率
     */
    public static void main(String[] args) {
        int [] arr = {1,4,2,5,7,3,7,7,4,9,1};
        selectionSort(arr);
        print(arr);
        int [] arr1 = {1,4,2,5,7,3,7,7,4,9,1};
        bubbleSort(arr1);
        print(arr1);
        int [] arr2 = {1,2,2,5,6,7,7,7,9,9,10};
        System.out.println(exist(arr2, 80));

    }
}