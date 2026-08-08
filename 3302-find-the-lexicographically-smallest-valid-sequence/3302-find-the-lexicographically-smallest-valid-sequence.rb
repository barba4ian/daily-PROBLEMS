# @param {String} word1
# @param {String} word2
# @return {Integer[]}
def valid_sequence(word1, word2)
    m = word1.length
    n = word2.length
    no_of_followers = Array.new(m, 0)
    result = Array.new(n, 0)
    i = m - 1
    j = n - 1
    matches = 0

    while i >= 0 && j >= 0
        if word1[i] == word2[j]
            matches += 1
            no_of_followers[i] = matches
            i -= 1
            j -= 1
        else
            no_of_followers[i] = matches
            i -= 1
        end
    end

    while i >= 0
        no_of_followers[i] = matches
        i -= 1
    end

    i = 0
    j = 0
    skipped = false

    while i < m && j < n
        if word1[i] == word2[j]
            result[j] = i
            i += 1
            j += 1
        elsif !skipped && i < m - 1 &&
              no_of_followers[i + 1] >= n - j - 1
            result[j] = i
            i += 1
            j += 1
            skipped = true
        else
            i += 1
        end
    end

    return [] if j < n

    result
end