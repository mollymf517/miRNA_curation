#This file uses the information from the genome alignment, and the file hg38.refGene.gtf to make a text file 
#listing the gene names of all the genes which sequences were aligned against. 

#Input:
    #1 - the sam output file from the genome alignment
    #2 - hg38.refGene.gtf


#create text file
echo > geneNames.txt


#go through each line from alignment file
while IFS= read -r line; do
    arr=($line);
    if [[  ${arr[0]} != "@SQ" && ${arr[0]} != "@PG" && ${arr[0]} != "@HD" ]]; then #only look at lines with relevant data
        #get chromosome number and starting nucleotide number of where the alignment occured
        chr=${arr[2]} 
        nt=${arr[3]}
        geneName=""
        while [[ $geneName == "" ]]; do #keep looking until a geneName has been found
            #look through lines in genome annotation file to find the name of the gene from the correct chromosome
            #with the alignment's starting nucleotide occuring between the gene's starting and ending nucleotides
            geneName=($(awk -v c=$chr -v n=$((nt)) '{if(n > $4 && n < $5 && c == $1)print$18}' $2))
            if [[ $geneName != "" ]]; then #if a gene name is found then format it properly
                geneName=`cut -b 2- <<< $geneName `
                geneName=$(echo $geneName | rev)
                geneName=`cut -b 3- <<< $geneName `
                geneName=$(echo $geneName | rev)
            fi

            if [[ $geneName == "" ]]; then #put other in as the gene name
                geneName="other"
            fi
        done   

        #write the gene name in the text file
        echo $geneName >> geneNames.txt
    fi
done < $1