class MedianFinder
    def initialize() = @arr = []

    def add_num(num)
        idx = @arr.bsearch_index {|x| x > num } || @arr.length
        @arr.insert(idx, num)
    end

    def find_median()
        n = @arr.length
        m = n / 2
        n.odd? ? @arr[m] : ( @arr[m - 1] + @arr[m] ) / 2.0
    end

end