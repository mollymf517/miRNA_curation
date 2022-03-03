#!/bin/bash
transid=$(echo | grep $1 hg38.refGene.gtf | awk '{ print $12 }')
echo $transid
#/global/home/hpc3982/resources/hg38.refGene.gtf
#echo $gene | awk '{ print "transcript id: " $12 }'
#grep $0 hg38.refGene.gtf | awk '{ print $12 }'
#awk '{ print $12 }' grep $0 hg38.refGene.gtf  
   # echo grep $1 hg38.refGene.gtf | less -p exon
   #echo hi
