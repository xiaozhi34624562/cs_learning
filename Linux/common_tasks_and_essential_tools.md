## package management
- package system
  - Debian Stlye: Debian, Ubuntu, Linux Mint, Raspbian
  - Red Hat Style: Fedoram, CentOS, Red Hat Enterprise Linux, OpenSUSE 
- how a package system works
  - package files
  - Repositories
  - dependencies
  - High and Low-level Package Tools
    - low-level tools which handle tasks such as installing and removing package files. Debian style(dpkg), Red Hat(rpm)
    - High-level tools that perform metadata searching and dependency resolution. Debian style(apt, apt-get, aptitude), Red Hat(yum, dnf)
<br>

-  **Common Package Management Tasks**(package_name refers to the actual name of a package rather than the term package_file, which is the name of the file that contains the package)
   - finding a package in a respository
     - Debian: apt-get update; apt-cache search search_string
     - Red Hat: yum search search_string e.g. yum search emacs 
   - installing a package from a respository
     - Debian: apt-get update; apt-get install package_name
     - Red Hat: yum install package_name
   - installing a package from a package file (If a package file has been downloaded from a source other than a repository, it can be in- stalled directly (though without dependency resolution) using a low-level tool)
     - Debian dpkg -i package_file
     - Red Hat rpm -i package_file
   - removing a package
     - Debian apt-get remove package_name
     - Red Hat yum erase package_name
   - updating packages from a respository
     - Debian apt-get update; apt-get upgrade
     - Red Hat: yum update 
   - updating a package from a package file
     - Debian dpkg -i package_file
     - Red Hat rpm -U package_file 
   - listing installed packages
     - Debian dpkg -l
     - Red Hat rpm -qa 
   - determing whether a package is installed
     - Debian dpkg -s package_name
     - Red Hat rpm -q package_name 
   - displaying information about an installed package
     - Debian apt-cache show package_name
     - Red Hat yum info package_name 
   - finding which package installed a file
     - Debian dpkg -S file_name
     - Red Hat rpm -qf file_name  
      
<br>

## storage media
******************************************** need some time to deal with it later
- `mount`: mount a file system
  - '/dev/sda2 on / type ext4 (rw)' device /dev/sda2 is mounted as the root file system, is of type ext4, and is both readable and writable (the option “rw”)
- `umount`: unmount a file system
- `fsck`: check and repair a file system
- `fdisk`: manipulate disk partition table
- `mkfs`: create a file system
- `dd`: convert and copy a file
- `genisoimage (mkisofs)`: create an ISO 9660 image file
- `wodim (cdrecord)`: write data to optical storage media
- `md5sum`: calculate an MD5 checksum 

<br>

## networking
- `ping`: send an ICMP ECHO_REQUEST to network hosts
- `traceroute(tracepath)`: print the route packets trace to a network host e.g. traceroute baidu.com 
- `ip`: show/manipulate routing, devices, policy routing and tunnels
  - lo: loopback interface, a virtual interface that the system uses to “talk to it- self”
  - eth0, is the Ethernet interface
- `netstat`: print network connections, routing tables, interface statistics, masquerade connections, and multicast memberships
- `ftp`: internet file transfer program
  - use `lftp` is better
  - lftp address(link)
  - lcd dir1 : change local directory
  - cd dir2 : change remote directory
  - get itemname : download file to dir1
  - bye : leave remote server
- `wget`: non-interactive network downloader
- `ssh`: openSSH SSH client(remote login program)
- `scp`: secure copy. cop a file from remote to local. `scp xiaozhi@ip:/home/xiaozhi/playground/dir1/photo.jpg .`
- `sftp`: secure replacement for ftp
  - sftp remote
  - ls
  - lcd
  - get filename
  - bye

<br>

## searching for files
- `locate`: find files by name
  -  if something wrong happens, you can use `updatedb`
  -  `locate String(filename)`locate will search its database of pathnames and output any that contain the string
- `find`: search for files in a directory hierarchy
  - find file Types `find ~ -type f`
    - b: Block special device file
    - c: Character special device file
    - d: Directory
    - f: regular file
    - l: Symbolic link
  - find size units `find ~ -type f -name "*.JPG" -size +1M`
    - b: 512-byte blocks. This is the default if no unit is specified.
    - c: Bytes
    - w: 2-byte words
    - k: kilobytes
    - M: megabytes
    - G: gigabytes
- `xargs`: build and execute command lines from standard input
- `touch`: change file times
- `stat`: display file or file system status 

<br>

## archiving and backup
<br>

## regular Expression
<br>

## text processing
<br>

## formatting output
<br>

## printing
<br>

## compiling programs

