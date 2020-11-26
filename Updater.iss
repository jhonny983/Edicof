; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Edicof"
#define MyAppVersion "3.0.0.0"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{9BC027C8-BE55-4246-86E1-717C9129B8F9}
AppName={#MyAppName}   
;AppVersion={param:foo|0.0.0.0}
AppVersion={#MyAppVersion}
AppVerName={#MyAppName} {#MyAppVersion}
DefaultDirName={pf32}\{#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=D:\OneDrive\My_Designs\EDICOF\OUTPUT
DisableProgramGroupPage=yes
OutputBaseFilename={#MyAppName}_Updater_{#MyAppVersion}
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
AlwaysShowComponentsList =true
Uninstallable =no
[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Files]
;Source: "C:\Users\Johnnatan\MIS DISE�OS\EDICOF\CONTROL DE VERSIONES\2.0.4.6\EDICOF\dist\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\OneDrive\My_Designs\EDICOF\CONTROL DE VERSIONES\Maven\Edicof\target\Edicof.exe"; DestDir: "{app}"; Flags: ignoreversion
;Source: "D:\OneDrive\My_Designs\EDICOF\CONTROL DE VERSIONES\Maven\Edicof\target\Edicof-3.0.1.1-jar-with-dependencies.jar"; DestDir: "{app}"; Flags: ignoreversion
;Source: "D:\OneDrive\MIS DISE�OS\EDICOF\CONTROL DE VERSIONES\Maven\INSTALADOR\Test.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\OneDrive\My_Designs\EDICOF\CONTROL DE VERSIONES\Edicof_Update_Downloader\dist\Edicof_Update_Downloader.jar"; DestDir: "{app}"; Flags: ignoreversion;
; NOTE: Don't use "Flags: ignoreversion" on any shared system files
;[run]
;Filename: {sys}\sc.exe; Parameters: "create Edicof start= auto binPath= ""{app}\Edicof.exe""" ; Flags: runhidden

;[UninstallRun]
;Filename: {sys}\sc.exe; Parameters: "stop Edicof" ; Flags: runhidden
;Filename: {sys}\sc.exe; Parameters: "delete Edicof" ; Flags: runhidden