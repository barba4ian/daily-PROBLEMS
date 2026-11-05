class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        # ABCDE - > AB-CDE
        s=s.replace("-", "").upper()
        res = []
        right = len(s)
        while right > 0:
            left = right - k if right - k > 0 else 0
            res.append(s[left:right])
            right -=k
        return "-".join(reversed(res))