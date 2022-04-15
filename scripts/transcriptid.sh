#!/bin/bash
#get the transcript id from human genome annotations using the gene name

transid=$(echo | grep $1 hg38.refGene.gtf | awk '{ print $12 }')
echo $transid
