import request from '@/utils/request'

// 查询土壤管理列表
export function listSoil(query) {
  return request({
    url: '/system/soil/list',
    method: 'get',
    params: query
  })
}

// 查询土壤管理详细
export function getSoil(id) {
  return request({
    url: '/system/soil/' + id,
    method: 'get'
  })
}

// 新增土壤管理
export function addSoil(data) {
  return request({
    url: '/system/soil',
    method: 'post',
    data: data
  })
}

// 修改土壤管理
export function updateSoil(data) {
  return request({
    url: '/system/soil',
    method: 'put',
    data: data
  })
}

// 删除土壤管理
export function delSoil(id) {
  return request({
    url: '/system/soil/' + id,
    method: 'delete'
  })
}
