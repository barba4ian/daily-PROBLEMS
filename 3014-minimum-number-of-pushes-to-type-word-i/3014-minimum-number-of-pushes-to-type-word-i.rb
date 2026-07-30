# @param {String} word
# @return {Integer}
def minimum_pushes(word, x = word.size)
  x + x / 8 * (x % 8) + x / 16 * 8 + x / 24 * 16
end