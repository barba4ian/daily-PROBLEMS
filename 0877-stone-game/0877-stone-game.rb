# @param {Integer[]} piles
# @return {Boolean}
def stone_game(piles)
    n = piles.length
    @dp = Array.new(n) { Array.new(n, -1) }
    @piles = piles
    max_diff(0, n - 1) >= 0
end

def max_diff(left, right)
    return 0 if left > right

    return @dp[left][right] if @dp[left][right] != -1

    left_score = @piles[left] - max_diff(left + 1, right)
    right_score = @piles[right] - max_diff(left, right - 1)

    @dp[left][right] = [left_score, right_score].max
    @dp[left][right]
end