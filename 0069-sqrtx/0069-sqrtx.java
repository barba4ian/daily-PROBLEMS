class Solution 
{
    public int mySqrt(int x) 
    {
        if (x == 0 || x == 1) return x;

        int l = 1;              
        int r = x;             
        int ans = 0;            

        while (l <= r) 
        {
            int m = l + (r - l) / 2;   
            if (m <= x / m) 
            {                       //  (avoid overflow)
                ans = m;
                l = m + 1;
            } else 
            {
                r = m - 1;
            }
        }

        return ans;
    }
}
