RESULT = ["Bob", "Tie", "Alice"]
MAX_TAKE = 3

def stone_game_iii(stone_value)
    stone_value += [0, 0]
    dp = Array.new(MAX_TAKE, 0)
    (stone_value.size - 1).downto(0) {|i|
        dp[i % MAX_TAKE] = (i...i+MAX_TAKE).collect {|j|
            stone_value[i..j].sum - dp[(j + 1) % MAX_TAKE]
        }.max
    }
    RESULT[(dp.first <=> 0) + 1]
end