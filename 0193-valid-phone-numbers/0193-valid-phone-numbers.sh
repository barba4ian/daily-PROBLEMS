# Read from the file file.txt and output all valid phone numbers to stdout.
regex='^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$'
while IFS= read -r line; do
    if [[ "$line" =~ $regex ]] || [[ "$line" =~ ^[0-9]{3}-[0-9]{3}-[0-9]{4}$ ]]; then
        echo "$line"
    fi
done < "file.txt"