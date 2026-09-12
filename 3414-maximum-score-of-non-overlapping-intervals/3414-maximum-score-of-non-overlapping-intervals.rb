def maximum_weight(intervals)
  n = intervals.size
  a = intervals.each_with_index.map { |v, i| [v[0], v[1], v[2], i] }
               .sort_by { |v| [v[0], v[1], v[3]] }
  s = a.map { |v| v[0] }

  # Tworzenie całkowicie niezależnych struktur w dp
  dp = Array.new(n + 1) { Array.new(5) { [0, []] } }

  is_lex = ->(x, y) do
    return true if y.empty?
    len = [x.size, y.size].min
    len.times do |j|
      return x[j] < y[j] if x[j] != y[j]
    end
    x.size < y.size
  end

  (n - 1).downto(0) do |i|
    _, r, w, id = a[i]
    low, high = 0, n
    while low < high
      m = (low + high) / 2
      s[m] > r ? high = m : low = m + 1
    end

    (1..4).each do |k|
      bw, bids = dp[i + 1][k]
      pw, pids = dp[low][k - 1]

      tw = pw + w
      tids = (pids + [id]).sort

      is_better = tw > bw || (tw == bw && is_lex.call(tids, bids))
      
      if is_better
        dp[i][k] = [tw, tids.dup]
      else
        dp[i][k] = [bw, bids.dup]
      end
    end
  end

  dp[0][4][1]
end