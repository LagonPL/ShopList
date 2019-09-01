## ShopList
Aplikacja z Listą Zakupów. W domyśle dzielona między znajomymi użytkownikami

## Firebase Rules
```
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "auth != null && auth.uid == $uid",
        ".write": "auth != null && auth.uid == $uid",
        "items": {
          "$item_id": {
            "title": {
              ".validate": "newData.isString() && newData.val().length > 0"
            },
              "amount": {
              ".validate": "newData.isString() && newData.val().length > 0"
            }
          }
        }
      }
    }
  }
}
```
```
{
  "rules": {
   	"nicknames": {
      "$nick_id":{
        "key" :{
          ".validate": "newData.isString() && newData.val().length > 0"
        },
        "nickname":{
          ".validate": "newData.isString() && newData.val().length > 0"
        }
      }
    }, 
    
    "users": {
        ".read": true,
        ".write": true,
      "$uid": {
        ".read": true,
        ".write": true,
          
        "nickname" : {
          "$nickname_id":{
            ".validate": "newData.isString() && newData.val().length > 0"
          }
        },
          
        "items": {
          "$item_id": {
            "title": {
              ".validate": "newData.isString() && newData.val().length > 0"
            },
            "amount": {
              ".validate": "newData.isString() && newData.val().length > 0"
            }
          }
        },
          
        "friends": {
          "$friend_id": {
              ".validate": "newData.isString() && newData.val().length > 0"
            },
        }
        
      }
    }
  }
}
```
