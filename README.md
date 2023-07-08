# PRM_SWD

**#Create git repository**
 - Khi đã clone project thì set up repo:
```
git remote add origin https://github.com/DuyNM-FU/PRM_SWD.git
```
 - Bắt buộc phải checkout ra nhánh riêng. Không được code trên MAIN/MASTER
 - Đặt tên nhánh theo tên của mình. VD: DuyNM.
```
git checkout -b <branch_name>
```
**#PULL & PUSH**
- Trước khi bắt đầu code và trước khi push code phải pull code về để tránh xóa code, mất code
 ```
git pull main origin
```
___Nếu xảy ra conflict thì so sánh code kĩ càng trên nhánh MAIN và nhánh của bạn để tránh việc mất code.___
 - Push code:
 ```
    git add .
    git commit -m "Your commit"
    git merge main
```
**#Sau khi push**
- Thông báo để được review code và accept merge.
