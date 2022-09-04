# carSystem1
SGM carSystem

### 配置信息
网关：localhost
端口：8080

mysql数据库：carsys
### 说明

## 数据库变更
### 【修改】 carmessage表
- 新增字段stock：int型，用于记录某款车型的具体数量


### 【新增】car_customer表
此表用于记录*车辆和用户的关系*，以下为表字段说明：
- id：主键，int型，表示车和用户关系的唯一性
- carSeries：车系，String型
- carName：品牌，String型
- addCarTime：加车时间，String型，记录车辆添加入库的时间，时间格式yyyy-MM-dd HH:mm:ss
- buyCarTime：购车时间，String型，记录用户购车时间，时间格式yyyy-MM-dd HH:mm:ss
- customer：顾客姓名，String型
- sold：售卖状态，String型，0：未售出；1：已售出


## 接口说明

### 1.【新增】表初始化
#### 说明：初始化‘carmessage’和‘car_customer’2张表，并向‘carmessage’插入数据
#### 请求类型：POST
#### 接口地址：/init/start
#### 请求头（无需额外入参）
#### 请求体（无需额外入参）
##### 请求示例
```
{}
```
#### 返回字段
| 字段ID | 字段名 | 中文名 | 类型      | 备注               |
|------|-----|----|---------|------------------|
| 1    | ok | 执行状态 | Boolean | true：成功；false：失败 |
| 2    | status | 接口状态 | Integer ||
| 3    | msg | 接口信息 | String  ||
| 4    | data | 数据 | Object  ||
##### 返回示例
```
{
    "status": 200,
    "msg": "OK",
    "data": null,
    "ok": true
}
```
### 2.【新增】添加库存
#### 请求类型：POST
#### 接口地址：/car/addStockById
#### 请求头（无需额外入参）
#### 请求体
| 字段ID | 字段名 | 中文名 | 类型 | 必填 |备注|
|------|-----|------|---------|---|-----|
| 1    | id | 车型id | Integer | Y ||
| 2    | addStock | 库存添加数量 | Integer | Y ||
##### 请求示例
```
{
    "id": 10,
    "addStock": 2
}
```
#### 返回数据
| 字段ID | 字段名 | 中文名 | 类型      | 备注               |
|------|-----|----|---------|------------------|
| 1    | ok | 执行状态 | Boolean | true：成功；false：失败 |
| 2    | status | 接口状态 | Integer ||
| 3    | msg | 接口信息 | String  ||
| 4    | data | 数据 | Object  ||

##### 返回示例
```
{
    "status": 200,
    "msg": "OK",
    "data": null,
    "ok": true
}
```
### 3.【新增】购车
#### 说明：顾名思义，就是购车咯，可以一次购入多台车，但是要根据库中*库存数量stock*判断
#### 请求类型：POST
#### 接口地址：/car/buyCar
#### 请求头（无需额外入参）
#### 请求体
##### 请求示例
| 字段ID | 字段名 | 中文名  | 类型      | 必填 |备注|
|------|-----|------|---------|---|-----|
| 1    | customer | 顾客姓名 | String  | Y ||
| 2    | carSeries | 车系   | String  | Y ||
| 3    | count | 购买数量 | Integer | Y ||
```
{
    "customer": "spxxkw",
    "carSeries": "君威",
    "count": 2
}
```
#### 返回数据
| 字段ID | 字段名 | 中文名 | 类型      | 备注               |
|------|-----|--|---------|------------------|
| 1    | ok | 执行状态 | Boolean | true：成功；false：失败 |
| 2    | status | 接口状态 | Integer ||
| 3    | msg | 接口信息 | String  ||
| 4    | data | 数据 | Array   ||
| 4.1  | id | 数据主键 | Integer ||
| 4.2  | carSeries | 车系 | String ||
| 4.3  | carName | 品牌 | String ||
| 4.4  | addCarTime | 加车时间 | String |时间格式yyyy-MM-dd HH:mm:ss|
| 4.5  | buyCarTime | 购车时间 | String |时间格式yyyy-MM-dd HH:mm:ss|
| 4.6  | customer | 顾客姓名 | String ||
| 4.7  | sold | 售卖状态 | String |0：未售出；1：已售出|
##### 返回示例
```
{
    "status": 200,
    "msg": "OK",
    "data": [
        {
            "id": 1,
            "carSeries": "君威",
            "carName": "别克",
            "addCarTime": "2022-09-03 22:50:00",
            "buyCarTime": "2022-09-04 17:39:15",
            "customer": "spxxkw",
            "sold": "1"
        },
        {
            "id": 2,
            "carSeries": "君威",
            "carName": "别克",
            "addCarTime": "2022-09-03 22:50:00",
            "buyCarTime": "2022-09-04 17:39:15",
            "customer": "spxxkw",
            "sold": "1"
        }
    ],
    "ok": true
}
```
### 4.【新增】车辆信息模糊查询（分页）
#### 说明：支持分页模糊关联查询字段‘carSeries’，‘carType’，‘carName’，其中关键字支持英文字符，数字，中文
#### 请求类型：POST
#### 接口地址：/car/fuzzyQueryCarByKeyWord
#### 请求头（无需额外入参）
#### 请求体
| 字段ID | 字段名      | 中文名  | 类型      | 必填  | 备注       |
|------|----------|------|---------|-----|----------|
| 1    | keyWord  | 关键字  | String  | N   | 为空默认全量查询 |
| 2    | pageSize | 每页数量 | Integer | Y   ||
| 3    | pageNum  | 页码   | Integer | Y   ||
##### 请求示例
```
{
    "keyWord": "T",
    "pageSize": 3,
    "pageNum": 1
}
```
#### 返回数据
| 字段ID | 字段名       | 中文名  | 类型      | 备注               |
|------|-----------|------|---------|------------------|
| 1    | ok        | 执行状态 | Boolean | true：成功；false：失败 |
| 2    | status    | 接口状态 | Integer ||
| 3    | msg       | 接口信息 | String  ||
| 4    | data      | 数据   | Array   ||
| 4.1  | id        | 数据主键 | Integer ||
| 4.2  | carSeries | 车系   | String  ||
| 4.3  | carName   | 品牌   | String  ||
| 4.4  | price     | 价格   | String  ||
| 4.5  | carType   | 车型   | String  ||
| 4.6  | stock     | 库存   | Integer ||
##### 返回示例
```
{
    "status": 200,
    "msg": "OK",
    "data": {
        "total": 2,
        "pageSize": 3,
        "pageNum": 1,
        "lastPage": 1,
        "resultList": [
            {
                "id": 7,
                "carName": "凯迪拉克",
                "carType": "SUV",
                "price": "35.5-45.0万",
                "carSeries": "XT5",
                "stock": 0
            },
            {
                "id": 8,
                "carName": "凯迪拉克",
                "carType": "B级轿车",
                "price": "128.5-170.0万",
                "carSeries": "CT6",
                "stock": 0
            }
        ]
    },
    "ok": true
}
```
### 5.【新增】车&车主关系模糊查询
#### 说明：支持分页模糊关联查询字段‘carSeries’，‘customer’，‘carName’，其中关键字支持英文字符，数字，中文
#### 请求类型：POST
#### 接口地址：/car/fuzzyQueryCarCustomerByKeyWord
#### 请求头（无需额外入参）
#### 请求体
| 字段ID | 字段名      | 中文名  | 类型      | 必填  | 备注       |
|------|----------|------|---------|-----|----------|
| 1    | keyWord  | 关键字  | String  | N   | 为空默认全量查询 |
| 2    | pageSize | 每页数量 | Integer | Y   ||
| 3    | pageNum  | 页码   | Integer | Y   ||
##### 请求示例
```
{
    "keyWord": "威",
    "pageSize": 3,
    "pageNum": 1
}
```
#### 返回数据
| 字段ID | 字段名 | 中文名 | 类型      | 备注               |
|------|-----|-----|---------|------------------|
| 1    | ok | 执行状态 | Boolean | true：成功；false：失败 |
| 2    | status | 接口状态 | Integer ||
| 3    | msg | 接口信息 | String  ||
| 4    | data | 数据  | Array   ||
| 4.1  | id | 数据主键 | Integer ||
| 4.2  | carSeries | 车系  | String ||
| 4.3  | carName | 品牌  | String ||
| 4.4  | addCarTime | 加车时间 | String |时间格式yyyy-MM-dd HH:mm:ss|
| 4.5  | buyCarTime | 购车时间 | String |时间格式yyyy-MM-dd HH:mm:ss|
| 4.6  | customer | 顾客姓名 | String ||
| 4.7  | sold | 售卖状态 | String |0：未售出；1：已售出|
##### 返回示例
```
{
    "status": 200,
    "msg": "OK",
    "data": {
        "total": 12,
        "pageSize": 5,
        "pageNum": 1,
        "lastPage": 3,
        "resultList": [
            {
                "id": 1,
                "carSeries": "君威",
                "carName": "别克",
                "addCarTime": "2022-09-03 22:50:00",
                "buyCarTime": "2022-09-04 17:39:15",
                "customer": "spxxkw",
                "sold": "1"
            },
            {
                "id": 2,
                "carSeries": "君威",
                "carName": "别克",
                "addCarTime": "2022-09-03 22:50:00",
                "buyCarTime": "2022-09-04 17:39:15",
                "customer": "spxxkw",
                "sold": "1"
            },
            {
                "id": 3,
                "carSeries": "君威",
                "carName": "别克",
                "addCarTime": "2022-09-03 22:50:00",
                "buyCarTime": null,
                "customer": null,
                "sold": "0"
            },
            {
                "id": 4,
                "carSeries": "君威",
                "carName": "别克",
                "addCarTime": "2022-09-03 22:50:00",
                "buyCarTime": null,
                "customer": null,
                "sold": "0"
            },
            {
                "id": 5,
                "carSeries": "君威",
                "carName": "别克",
                "addCarTime": "2022-09-03 22:50:00",
                "buyCarTime": null,
                "customer": null,
                "sold": "0"
            }
        ]
    },
    "ok": true
}
```