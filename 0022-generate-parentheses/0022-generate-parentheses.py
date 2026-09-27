class Solution:
    def generateParenthesis(self, n: int) -> list[str]:
        lst= []
        self.generate(n,'',lst,0,0)
        return lst
    def generate(self,n,s,lst,open,close):
        if len(s)==2*n:
            lst.append(s)
            return
        if open < n:
            self.generate(n,s+'(',lst,open+1,close)
        if open > close:
            self.generate(n,s+')',lst,open,close+1)