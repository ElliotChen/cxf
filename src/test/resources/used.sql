UPDATE JOB SET STATE = 'Created' 
DELETE FROM EXECUTION 
java -cp h2*.jar org.h2.tools.Server -web