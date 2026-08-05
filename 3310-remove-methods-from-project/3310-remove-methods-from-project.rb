# @param {Integer} n
# @param {Integer} k
# @param {Integer[][]} invocations
# @return {Integer[]}
def remaining_methods(n, k, invocations)
  res = []
  graph = Hash.new { |h, key| h[key] = [] }
  mark = Array.new(n, 0)
  outside_connection = false

  invocations.each { |e| graph[e[0]] << e[1] }

  bfs = lambda do |color, src|
    q = [src]
    mark[src] = color

    until q.empty?
      node = q.shift
      next unless graph.key?(node)

      graph[node].each do |nxt|
        if mark[nxt] == 1 && color == 2
          outside_connection = true
          return
        end
        if mark[nxt] != color
          mark[nxt] = color
          q << nxt
        end
      end
    end
  end

  bfs.call(1, k)

  (0...n).each do |i|
    next if i == k || mark[i] == 1
    bfs.call(2, i)
  end

  (0...n).each do |i|
    next if !outside_connection && mark[i] == 1
    res << i
  end

  res
end