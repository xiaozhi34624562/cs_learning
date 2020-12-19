# Learning the shell
## What is shell
- if the last character of the prompt is "#", the terminal session has superuser priviliege.
- Holding down the left mouse button and dragging the mouse over it, it is copied into buffer maintained.
- date: show the current time and date
- cal: displays a calendar of thr current month
- df: the current amount of the free space on our disk drivers
- free: display the amount of free memory
- Ending a terminal session: closing the terminal emulator window; entering `exit` ; Ctrl-d;
## Navigation
- pwd: print working directory
- cd: Change directory
  - absolute pathnames: by the leading slash in the pathname
  - relative pathnames: `.`means the working directory; `..`means the working directory's parent directory
  - when we want to change the working directory(/usr/bin) to the parent directory, we can: using an absolute pathname `cd /usr`; a relative pathname `cd ..` 
  - in most cases we can omit "./"
  - cd - : change the working directory to the previous working directory
  - cd ~user_name: change the working directory to the home directory of user_name, e.g. ~bob
- ls: List directory contents
  - ls -a: can show hidden files
  - do not embed spaces in filenames
## Exploring the system
- ls: list directory contents
  - `ls /usr` specify the directory to list 
  - ` ls ~ /usr` specify multiple directions(uesr's home directory and /usr directory) 
  - `ls -l` change the output to the long format 
  - **command -options arguments** most commands look kind of like this. long options, consisting of a word preced by two dashes
    - -a: list all files including hidden files
    - -A: list all files except `.` and `..`
    - -d: list the contents of the directory, not the directory itself
    - -F: append an indicator character to the end of each listed name. slash for the directory.
    - -h: in long format listings, display file sizes in human readable format rather than in bytes
    - -l: display results in long format
    - -r: display results in reverse order(in ascending alphabetival order)
    - -S: sort results by file size
    - -t: sort by modification time
  - ` -rw-r--r-- 1 root root  34391 2017-04-03 11:05 logo-Ubuntu.png` 
    - '-' means this is a file; d means this is a directory
    - 1: file's number of hard links
    - root: the username of the file's owner
    - root: the name of the group the owns the file
    - 32059: size of the file in bytes
- file: determine file type 
  - `file filename` : will print a brief description of the file's contents
- less: view file contents
#### Directories Found on Linux Systems
- /: the root directory
- /bin: contains binaries that must be present for the system to boot and run 
- /boot: contains the linuc kernel, initial RAM disk image and the boot loader
  - /boot/grub/grub.conf or menu.lst, which are used to configure the boot loader
  - /boot/vmlinuz: the linux kernel
- /dev: contains device nodes
- /etc: contains all of the system-wide configuration files. everything in this directory is text. contains shell scripts
  - /etc/crontab: a file that defines when automated jobs will run
  - /etc/fstab: a table of storage devices and their associated mount points
  - /etc/passwd: a list of the user accounts
- /home: each user is given a directory in /home
- /lib: contains shared library files used by the core system programs
- /lost+founf: Each formatted partition or device using a Linux file system, such as ext4, will have this directory. It is used in the case of a partial recovery from a file system corruption event. Unless something really bad has happened to our system, this directory will remain empty.
- /media: mount points for removeable media such as USB, CD-ROM
- /mnt: contains mount points for removable devices that have been mounted
manually.
- /opt :used to install “optional” software. This is mainly used to hold commercial software products that might be installed on the system.
- /proc: The /proc directory is special. It's not a real file system in the sense of files stored on the hard drive. Rather, it is a virtual file system maintained by the Linux kernel. The “files” it contains are peepholes into the kernel itself. The files are readable and will give us a picture of how the kernel sees the computer.
- /root: This is the home directory for the root account
- /sbin: This directory contains “system” binaries. These are programs that perform vital system tasks that are generally reserved for the superuser.
- /tmp: intended for the storage of temporary, transient files created by various programs. 
- /usr: contains all the programs and support files used
by regular users.
- /usr/bin: contains the executable programs installed by the linux distribution
- /usr/lib: shared libraries
- /usr/local:
- /usr/sbin: Contains more system administration programs
- /usr/share: contains all the shared data used by programs in /usr/bin. This includes things such as default configuration files, icons, screen backgrounds, sound files, etc.
- /usr/shate/doc:documentation files organized by package.
- /var: data that is likely to change is stored. Various databases, spool files, user mail, etc. are located here.
- /var/log: contains log files, records of various system activity. These are important and should be monitored from time to time. The most useful ones are /var/log/messages and /var/log/syslog. Note that for security reasons on some systems only the superuser may view log files.
## Manipulating Files and Directories
- Wildcards
  - '*' matches any characters
  - ? matchrs any single character
  - [characters] matches any character that is a member of the set characters
  - [!characters] matches any character that is not a member of the set characters
  - [[:class:]] matches any character that is a member of the specified class
    - [:alnum:] matches any alphanumeric character
    - [:alpha:] matches any alphabetic character
    - [:digit:] matches any numeral
    - [:lower:] matches any lowercase letter
    - [:upper:] matches any uppercase letter
  - e.g. \*; g*; b*.txt; Data???; [abc]*; BACKUP.[0-9][0-9][0-9]; [[:upper:]]\*; 
- cp item... directory: copy multiple files and directories into a directory
  - -a: Copy the files and directories and all of their attributes, including ownerships and permissions. Normally, copies take on the default attributes of the user performing the copy. 
  - -i: Before overwriting an existing file, prompt the user for confirmation. If this option is not specified, cp will silently (meaning there will be no warning) overwrite files.
  - -r: Recursively copy directories and their contents. This option (or the -a option) is required when copying directories.
  - -u When copying files from one directory to another, only copy files that either don't exist or are newer than the existing corresponding files, in the destination directory. This is useful when copying large numbers of files as it skips files that don't need to be copied.
  - -v: Display informative messages as the copy is performed.
  - e.g. cp file1 file2; cp -i file1 file2; cp file1 file2 dir1; cp dir1/* dir2; cp -r dir1 dir2
- mv item... directory: move/rename files and directories
  - options -i; -u; -v
- mkdir directory...: create directories;
  - three periods mean that the argument could be repeated
  - e.g. mkdir dir1 dir2 dir3
- rm item...: remove files and directories
  - -i: Before deleting an existing file, prompt the user for confirmation. If this option is not specified, rm will silently delete files
  - -r
  - -f: Ignore nonexistent files and do not prompt. This overrides the --interactive option
  - -v: Display informative messages as the deletion is performed.
- ln: create hard and symbolic links
  - `ln file link` a hard link, a hard link like a backup of the file.
  - `ln -s item link` a symbolic link,  contains a text pointer to the referenced file or directory, if we write something to the symbolic link, the refer- enced file is written to. However when we delete a symbolic link, only the link is deleted, not the file itself 
  - difference:A hard link may not reference a directory; A hard link cannot reference a file outside its own file system
## Working with Commands
## Redirection
## Seeing the World as the Shell Sees It
## Advanced Keyboard Tricks
## Permissions
## Processes
