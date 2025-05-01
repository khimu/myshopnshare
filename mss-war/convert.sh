#!/bin/bash

SRC_DIR="./src"           # or wherever your .java files are
DST_DIR="./manuscript"

mkdir -p "$DST_DIR"

find "$SRC_DIR" -name "*.java" | while read -r file; do
  class=$(basename "$file" .java)
  out="$DST_DIR/$class.md"

  echo "# $class" > "$out"
  echo "" >> "$out"
  echo '```java' >> "$out"
  cat "$file" >> "$out"
  echo '```' >> "$out"
done

