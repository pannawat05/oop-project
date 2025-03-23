#!/bin/bash
# clear
# Loop through all wav files in the current directory
for file in *.wav; do
    # Skip if no wav files found
    [ -f "$file" ] || continue

    echo "Processing: $file"

    # Create temporary output filename
    temp_output="temp_${file}"

    # Convert the file to new sample rate and format
    ffmpeg -i "$file" -ac 2 -ar 22050 -sample_fmt s16 "$temp_output"

    # If conversion successful, replace original file with converted file
    if [ $? -eq 0 ]; then
        mv "$temp_output" "$file"  # Move the temp file to the original filename
        echo "Converted and replaced: $file"
    else
        echo "Failed to convert: $file"
        rm -f "$temp_output"  # Clean up temporary file if conversion fails
    fi
done
