class Solution:                                 
    def sortArray(self,nums:list[int]) -> list[int]:    #   Example: [3,3,1,8,6,5,5,5,5]

        ctr  = Counter(nums)                            #   ctr = {5:4, 3:2, 1:1, 8:1, 6:1}

        return list(chain(*([i]*ctr[i]                  #   return    list(chain( *([1]*1, [3]*2, [5]*4, [6]*1, [8]*1) ))   
                    for i in range(min(ctr),            #           = list(chain([1], [3,3,3], [5,5,5,5], [6], [8] ))
                    max(ctr)+1) if i in ctr)))          #           = [1, 3,3, 5,5,5,5, 6, 8]   
                    