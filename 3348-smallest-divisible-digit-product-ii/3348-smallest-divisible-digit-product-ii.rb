# Precompute digit factorizations
FACTORS = {
  0 => [0, 0, 0, 0], 1 => [0, 0, 0, 0], 2 => [1, 0, 0, 0],
  3 => [0, 1, 0, 0], 4 => [2, 0, 0, 0], 5 => [0, 0, 1, 0],
  6 => [1, 1, 0, 0], 7 => [0, 0, 0, 1], 8 => [3, 0, 0, 0],
  9 => [0, 2, 0, 0]
}

# Precompute minimum digits needed for a required count of 2s and 3s.
# 60 and 40 are safely bounded above the max possible prime counts for 10^14.
MIN_LEN = Array.new(65) { Array.new(45, 999999) }
MIN_LEN[0][0] = 0
(0..60).each do |i|
  (0..40).each do |j|
    next if i == 0 && j == 0
    res = 999999
    res = [res, MIN_LEN[[i - 1, 0].max][j] + 1].min                 # using 2
    res = [res, MIN_LEN[i][[j - 1, 0].max] + 1].min                 # using 3
    res = [res, MIN_LEN[[i - 2, 0].max][j] + 1].min                 # using 4
    res = [res, MIN_LEN[[i - 1, 0].max][[j - 1, 0].max] + 1].min    # using 6
    res = [res, MIN_LEN[[i - 3, 0].max][j] + 1].min                 # using 8
    res = [res, MIN_LEN[i][[j - 2, 0].max] + 1].min                 # using 9
    MIN_LEN[i][j] = res
  end
end

def get_prime_factors(t)
  c2 = c3 = c5 = c7 = 0
  while t % 2 == 0; c2 += 1; t /= 2; end
  while t % 3 == 0; c3 += 1; t /= 3; end
  while t % 5 == 0; c5 += 1; t /= 5; end
  while t % 7 == 0; c7 += 1; t /= 7; end
  [c2, c3, c5, c7, t]
end

# @param {String} num
# @param {Integer} t
# @return {String}
def smallest_number(num, t)
  c2, c3, c5, c7, rem_t = get_prime_factors(t)
  
  # If t has prime factors greater than 7, it's impossible to form with digits 1-9
  return "-1" if rem_t > 1
  
  n = num.length
  
  # Check if `num` is already zero-free and completely valid
  unless num.include?('0')
    cur2 = cur3 = cur5 = cur7 = 0
    num.each_char do |ch|
      f = FACTORS[ch.to_i]
      cur2 += f[0]; cur3 += f[1]; cur5 += f[2]; cur7 += f[3]
    end
    if cur2 >= c2 && cur3 >= c3 && cur5 >= c5 && cur7 >= c7
      return num
    end
  end
  
  pref2 = Array.new(n + 1, 0)
  pref3 = Array.new(n + 1, 0)
  pref5 = Array.new(n + 1, 0)
  pref7 = Array.new(n + 1, 0)
  
  first_zero = n
  
  # Tabulate prefix sums of factors leading up to any index
  (0...n).each do |idx|
    d = num[idx].to_i
    first_zero = idx if d == 0 && first_zero == n
    
    f = FACTORS[d]
    pref2[idx+1] = pref2[idx] + f[0]
    pref3[idx+1] = pref3[idx] + f[1]
    pref5[idx+1] = pref5[idx] + f[2]
    pref7[idx+1] = pref7[idx] + f[3]
  end
  
  # Try replacing the rightmost valid digit to form a lexicographically smallest sequence
  start_i = [n - 1, first_zero].min
  start_i.downto(0) do |i|
    start_d = (num[i].to_i) + 1
    (start_d..9).each do |d|
      f = FACTORS[d]
      p2 = pref2[i] + f[0]
      p3 = pref3[i] + f[1]
      p5 = pref5[i] + f[2]
      p7 = pref7[i] + f[3]
      
      req2 = [c2 - p2, 0].max
      req3 = [c3 - p3, 0].max
      req5 = [c5 - p5, 0].max
      req7 = [c7 - p7, 0].max
      
      rem_len = n - 1 - i
      
      # If the remaining spaces can sufficiently satisfy all the required primes 
      if MIN_LEN[req2][req3] + req5 + req7 <= rem_len
        res = num[0...i] + d.to_s
        res_arr = []
        curr_rem = rem_len
        
        # Sequentially greedily pick the lowest valid digit for all remaining indexes
        curr_rem.times do
          (1..9).each do |nd|
            nf = FACTORS[nd]
            nreq2 = [req2 - nf[0], 0].max
            nreq3 = [req3 - nf[1], 0].max
            nreq5 = [req5 - nf[2], 0].max
            nreq7 = [req7 - nf[3], 0].max
            
            if MIN_LEN[nreq2][nreq3] + nreq5 + nreq7 <= curr_rem - 1
              res_arr << nd.to_s
              req2, req3, req5, req7 = nreq2, nreq3, nreq5, nreq7
              curr_rem -= 1
              break
            end
          end
        end
        return res + res_arr.join
      end
    end
  end
  
  # If changing digits keeping length doesn't work, construct the smallest longer valid number string
  new_len = [n + 1, MIN_LEN[c2][c3] + c5 + c7].max
  res_arr = []
  req2, req3, req5, req7 = c2, c3, c5, c7
  
  curr_rem = new_len
  curr_rem.times do
    (1..9).each do |nd|
      nf = FACTORS[nd]
      nreq2 = [req2 - nf[0], 0].max
      nreq3 = [req3 - nf[1], 0].max
      nreq5 = [req5 - nf[2], 0].max
      nreq7 = [req7 - nf[3], 0].max
      
      if MIN_LEN[nreq2][nreq3] + nreq5 + nreq7 <= curr_rem - 1
        res_arr << nd.to_s
        req2, req3, req5, req7 = nreq2, nreq3, nreq5, nreq7
        curr_rem -= 1
        break
      end
    end
  end
  
  res_arr.join
end