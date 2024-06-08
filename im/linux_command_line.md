## mkdir
- mkdir -p /etc/docker 
    - if there is the fold, it will do nothing, if not create new folder. if there is not parent folder, create parent folder

## sed
- sed -i 's/orginalString/targetString/g' fileName
    - replace originalString with targetString for the global text file in original file; if you want to replace it in new file, you can use `-i.bak`;
    - sed also can delete some lines;

## tee
- tee [option] [file]
    - read from standard input and write to both standard output and one or more files simultaneously.
    - option could be `-a` appenf the file; `-i` ignore interrupt signals
    - shows write multiple lines into deamon.json, the multiple lines start and end with 'EOF'
    ```
    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
        "debug": true,
        "data-root": "/var/lib/docker"
    }
    EOF

    ```

## typical docker command line
- systemctl daemon-reload // 重载系统服务
- systemctl list-unit-files --type service // 查看全部服务命令
- systemctl status docker // 查看docker服务状态
- systemctl enable docker // 设置docker开机自启动
- systemctl disable docker // 关闭docker开机自启动
- systemctl start docker // 启动docker服务
- systemctl stop docker // 停止docker 服务
- systemctl restart docker // 重启docker服务

## curl
- basic download and `-O` means download the file and save it with its original name `curl -O http://example.com/file.txt` if you use `curl -O -# http://example.com/file.txt` you could see a progress bar
- specify a filename `curl -o newfile.txt http://example.com/file.txt
`
- follow redirections eseapically useful when downloading `curl -L http://example.com`
- show detail process `curl -v http://example.com`
- send get request `curl http://example.com`
- send post request `curl -X POST -d "param1=value1&param2=value2" http://example.com` explan: `-X` specific the request method and `-d` sends data with the request
- send Json data for post request `curl -X POST -H "Content-Type: application/json" -d '{"key1":"val1", "key2":"val2"}' http://example.com` explan: `-H` option sets in request head
- save output to a file   `curl http://example.con -o output.txt`
- limit download speed `curl --limit-rate 100k http://example.com`
- upload a file `curl -F "file=@/path/to/file" http:/example.com`
- handling HTTPS request
    - ignore SSL certificate verification `curl -k https://example.com` where `-k` ignore SSL certificate verification errors
    - specify a CA certificate `curl --cacert /path/to/cacert.pem https://example.com` 

## ln
Soft links (symbolic links) and hard links are two different methods used in Unix-like operating systems to reference files. Here are the key differences between them:

### Soft Link (Symbolic Link)

1. **Nature**:
   - A soft link is a shortcut to the original file.
   - It is an independent file that contains a path to the original file.

2. **Inode**:
   - Soft links have a different inode number from the original file.
   - They point to the original file by its pathname.

3. **Cross Filesystem Boundaries**:
   - Soft links can link across different filesystems.

4. **File Deletion**:
   - If the original file is deleted, the soft link becomes a "dangling" link (i.e., it points to a non-existent file).

5. **Creation**:
   - Created using the `ln -s` command (e.g., `ln -s /path/to/original /path/to/link`).

6. **Size**:
   - The size of a soft link is the length of the pathname of the file it points to.

### Hard Link

1. **Nature**:
   - A hard link is an additional directory entry for the same file.
   - It is essentially another name for the same file.

2. **Inode**:
   - Hard links share the same inode number with the original file.
   - They reference the same physical data on the disk.

3. **Cross Filesystem Boundaries**:
   - Hard links cannot link across different filesystems. They must be on the same filesystem as the original file.

4. **File Deletion**:
   - If the original file is deleted, the data still exists as long as at least one hard link references it. The file is only truly deleted when all hard links are removed.

5. **Creation**:
   - Created using the `ln` command without any options (e.g., `ln /path/to/original /path/to/link`).

6. **Size**:
   - The size of a hard link is the same as the original file because they are indistinguishable at the filesystem level.

### Summary
- **Soft links** are flexible and can link across filesystems, but they depend on the existence of the original file.
- **Hard links** are more rigid, being confined to the same filesystem, but they provide redundancy, ensuring that the data persists until all hard links are deleted.