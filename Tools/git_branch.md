## Git分支的本质
Git保存的不是文件的变化或者差异，而是一系列不同时刻的快照。  
在进行提交操作时，git会保存一个提交对象，这个提交对象里面包含指向暂存内容快照的指针，还包含了作者的姓名、邮箱、提交时输入的信息，指向父对象的指针（首次提交时没有父指针，多个分支合并产生的提交对象有多个父对象）  
Git的分支，本质上是指向提交对象的可变指针。  
<br>

## Git创建分支
`git branch branch1` 创建分支branch1，此时的HEAD依然指向master。`git checkout branch1`HEAD指向branch1（切换到branch1分支）。也可以使用`git checkout -b branch1`创建branch1，并转换至branch1（即HEAD指向branch1）
<br>

## 分支的新建与合并
- 开发一个网站，需要创建一个新的分支 `git branch dev`
- 在分支上展开工作 `git checkout dev`
- 突然接到紧急任务，需要紧急修补
  - 保存现有分支的修改 `git stash` `git checkout master`
  - 切换到新的分支，在分支中修复问题 `git checkout -b fix`
  - 解决问题后，切回线上分支 `git commit`, `git checkout master `
  - 合并修补分支, 然后删除无用的分支 `git merge fix`， `git branch -d fix`
  - 将改动的推到线上 ` git push`
  - 切回开始的分支，继续工作 `git stash apply`
- merge时，如果遇到问题，可以用`git status` 来查看问题，打开冲突的文件进行修改。 之后`git add .`上传文件到暂存区，如果没有问题，可以提交。
<br>

## 分支管理
- `git branch` 展示所有分支，带星号的为当前分支
- `git branch -v` 展示都有分支，及每个分支的最近一次提交信息
- `git branch --merged` 显示所有merged的分支
- `git branch --no-merged` 显示没有merge的分支
- `git branch -d branchName` 删除分支（已经merge过的）
- `git branch -D branchName` 删除分支（没有merge过的）
<br>
 
 ## 远程分支
 - `git remote show <remote>` 可以获取远程分支的消息
 - `git remote add <shortname> <remote>` 添加远程仓库
 - `git fetch <remote>` 抓取本地没有的数据
 - `git push <remote> <branch>` 把当前分支的内容推送到remote去
  #### 跟踪分支
 - `git checkout -b <newBranch> <remote>/<branch>` 在远端分支的基础上建立一个本地分支
 - `git checkout --track <remote>/<branch>` 作用同上
 - `git branch -u <remote>/<branch>`设置已有的本地分支跟踪一个刚刚拉取下来的远程分支，或者想要修改正在跟踪的上游分支
 - `git branch -vv` 查看设置的所有跟踪分支
  #### 删除远端分支
 - `git remote <remote> --delete <branch>` 