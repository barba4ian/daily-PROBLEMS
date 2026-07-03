class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int   i=0;
      int j=0;
      int k=0;
      int n=nums1.length;
      int m=nums2.length;
      int []arr=new int [n+m];
        while(i<n||j<m){
            if(i<n&&j<m)
            {
            if(nums1[i]<nums2[j]){
                arr[k++]=nums1[i++];
            }
            else{
                arr[k++]=nums2[j++];
            }
            }
            else if(i<n){
                arr[k++]=nums1[i++];
            }
            else{
                arr[k++]=nums2[j++];
            }
        }
        float result;
        int  mid=arr.length/2;
        if(arr.length%2==0){
            result=(arr[mid]+arr[mid-1])/2.0f;
        }
        else{
            result=arr[mid];
        }
        return result;
    }
}