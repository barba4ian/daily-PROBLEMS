class Solution:                                                   
     def canSeePersonsCount(self, heights: List[int]) -> List[int]: 
                                                  
                                                  
                                                   
                                                   
        ans, stack = [0] * len(heights), deque()    
                                                     
        for i, hgt in enumerate(heights[::-1]):
                                                   
            while stack and stack[-1] < hgt:      
                ans[i]+= 1                         
                stack.pop()                        
                                                 
            if stack: ans[i]+= 1                  
                                               
            stack.append(hgt)
                                                   
        return ans[::-1]