# git usage
## configration
 - show all config 
   - `git config --list --local/global/system` 
 - set global configuration, this is for all repositories
   - `git config --global user.name 'name` 
   - `git config --global user.email 'email_address`
 - set local configuration, this is for the current repository and has **higher priority**
   - `git config --local user.name 'name` 
   - `git config --local user.email 'email_address`
## rename
- orignal method (readme old name, readme.md new name)
    - `mv readme readme.md`
    - `git add readme.md`
    - `git rm readme`
-  remove all changes but danger 
   -  `git reset --hard` 
-  better method
   -  `git mv readme readme.md`
## log
- show the latest info
  - `git log --oneline`
- show the latest 4 infos
  - `git log -n4 --oneline`
- show local branches
  - `git branch -v`
- create branch
  - `git checkout -b temp 4323424` 
## .git insight
- HEAD (equal to git checkout user)
- config (user name and user email, equal to git config --local user.name/email '')
- objects
- refs
## commit, tree, blob
每一个commit里面都有tree，tree里面有blob，blob里面是文件
## show differences
- `git diff HEAD HEAD~1` the diff between current and last one
- `git diff HEAD HEAD~2` the diff between current and last two
- `git diff --cached` diff between commited and cached 
## delete/add a branch
- `git branch -D branchName` will delete a branch
- `git branch newBranchName` make a new branch based on the current branch
- `git branch newBranchName existBranchName` make a new branch based on the existBranchName branch
## change the commit
- `git commit --amend` change the latest commit
- `git rebase -i numberOfTargetslastCommitNumber` change the target commit or make servel commits into one
  