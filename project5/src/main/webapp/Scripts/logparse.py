logFile = '../log/SearchPerformance.log'
f=open(logFile,'r')
tsSum=0
tjSum=0
tsNum=0
tjNum=0

for line in f:
    t1,t2=line.split(':')
    if t1=='TS':
        tsSum+=int(t2)
        tsNum=tsNum+1
    if t1=='TJ':
        tjSum+=int(t2)
        tjNum=tjNum+1

f.close()

tsMean=float(tsSum)/float(tsNum)
tjMean=float(tjSum)/float(tjNum)

resultFile='TSJSResult.txt';
f=open(resultFile,'w')

f.write("TS total time:"+str(tsSum)+'\n')
f.write("TS number:"+str(tsNum)+'\n')
f.write("TS average time:"+str(tsMean)+'\n')

f.write("TJ total time:"+str(tjSum)+'\n')
f.write("TJ number:"+str(tjNum)+'\n')
f.write("TJ average time:"+str(tjMean)+'\n')

f.close()
