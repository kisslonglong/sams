import request from '@/utils/request'

// 查询农田信息管理列表
export function listFarmland(query) {
  return request({
    url: '/system/farmland/list',
    method: 'get',
    params: query
  })
}

// 查询农田信息管理详细
export function getFarmland(id) {
  return request({
    url: '/system/farmland/' + id,
    method: 'get'
  })
}

// 新增农田信息管理
export function addFarmland(data) {
  return request({
    url: '/system/farmland',
    method: 'post',
    data: data
  })
}

// 修改农田信息管理
export function updateFarmland(data) {
  return request({
    url: '/system/farmland',
    method: 'put',
    data: data
  })
}

// 删除农田信息管理
export function delFarmland(id) {
  return request({
    url: '/system/farmland/' + id,
    method: 'delete'
  })
}


export function allSoil(){
  return request({
    url: '/system/farmland/all',
    method: 'get'
  })
}

export function mapInfo(params){
  return request({
    url: '/system/farmland/mapInfo',
    method: 'get',
    params: params
  })
}

export function template(query) {
  return request({
    url: '/system/farmland/template',
    method: 'get',
    params: query
  })
}