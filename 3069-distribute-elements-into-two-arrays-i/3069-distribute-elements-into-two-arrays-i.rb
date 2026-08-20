# @param {Integer[]} nums
# @return {Integer[]}
def result_array((x, y, *a))
  a.reduce([[x], [y]]) { |(u, v), n| x > y ? [u << (x = n), v] : [u, v << (y = n)]}.flatten
end