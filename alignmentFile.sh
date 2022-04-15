#This file creates the files that visualize alignment 

#input:
    #1 - precursors.fa (fasta file used to create precursor indexes)
    #2 - sam file (output file from precursor alignment)

#Create a file named after each precursor and write precursor sequences to corresponding file

while IFS= read -r line; do
    start=${line:0:1}  #readingin through precursor file
    #Look for line in format "> NR_XXXXX" and name a file this
    if [[ $start == ">" ]]; then
        next=${line:1:2}
        if [[ $next == "NR" ]]; then
            file="${line:1:9}.txt"    
            echo > $file
        fi
    elif [[ $start == "N" ]]; then
        file="$line.txt"
        echo > $file 
    else
        echo $line > $file
    fi


done < $1


#go through sam file and for each alignment get precursor name
while IFS= read -r line; do

    arr=($line)

    if [[  ${arr[0]} != "@SQ" && ${arr[0]} != "@PG" && ${arr[0]} != "@HD" ]]; then
        if [[ ${arr[2]:0:2} == "NR" ]]; then
            seq=${arr[-11]}
            precName=${arr[2]}
            startPt=${arr[3]}
            let startPt=$startPt-1

            str=$(printf "%*s%s" $startPt ' ' "$seq")
            file="$precName.txt"

            echo "$str" >> $file

        fi


    fi

done < $2

 