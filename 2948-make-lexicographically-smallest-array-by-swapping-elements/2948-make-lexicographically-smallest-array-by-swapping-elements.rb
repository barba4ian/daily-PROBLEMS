def lexicographically_smallest_array(nums, limit)
    chunks = nums.sort
    .chunk_while{ _2 - _1 <= limit}
    .to_a

    group = {}

    chunks.each_index{ |i|
        chunks[i].each{ |n|
            group[n] = i
        }
    }

    nums.map{ |n|
        i = group[n]
        chunks[i].shift
    }
end