$lines  = Get-Content C:\Users\David\Documents\GitHub\Real-Time-Mafia-Bot\Distribution_Stuff\emails.txt
$lines | foreach {($_.ToString() -split " : ")[1]}
