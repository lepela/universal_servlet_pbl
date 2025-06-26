Ubuntu에서 MariaDB를 **완전히 제거**하려면 아래 순서대로 진행하면 깔끔하게 지워져요.
단순히 `apt remove`만 하면 설정 파일이 남기 때문에, **설정 + 데이터 + 패키지** 전부 제거하는 방식으로 할게요.

---

## ✅ 1단계: 서비스 중지

```bash
sudo systemctl stop mariadb
```

---

## ✅ 2단계: 패키지 제거

```bash
sudo apt remove --purge mariadb-server mariadb-client mariadb-common -y
```

* `--purge`: 설정 파일도 같이 제거
* `-y`: 자동 yes

---

## ✅ 3단계: 남은 패키지 정리

```bash
sudo apt autoremove -y
sudo apt autoclean
```

---

## ✅ 4단계: 설정 및 데이터 디렉토리 수동 삭제

```bash
sudo rm -rf /etc/mysql
sudo rm -rf /var/lib/mysql
sudo rm -rf /var/log/mysql
sudo rm -rf /var/log/mysql.*
```

> `lib/mysql`은 실제 DB 데이터, `etc/mysql`은 설정 파일

---

## ✅ 5단계: 사용자 삭제 (선택 사항)

```bash
sudo deluser mysql
sudo delgroup mysql
```

> 혹시나 `mysql` 사용자 계정이 남아있다면

---

## ✅ 6단계: 확인

```bash
which mysql
```

→ 아무것도 안 뜨면 성공

```bash
dpkg -l | grep mariadb
```

→ 관련 패키지가 아무것도 안 뜨면 완전 제거 완료

---

## 💡 재설치 필요할 땐

```bash
sudo apt update
sudo apt install mariadb-server mariadb-client
```

---

