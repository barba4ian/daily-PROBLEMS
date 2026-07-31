
def minimum_pushes(word)
    h = Hash.new
    word.each_char do |l|
        h[l] ||= 0
        h[l] += 1
    end

    count = 1
    multiplyer = 1
    sum = 0
    h.values.sort.reverse.each do |i|
        sum += i * multiplyer

        multiplyer += 1 if count%8 == 0
        count += 1
    end

    sum
end