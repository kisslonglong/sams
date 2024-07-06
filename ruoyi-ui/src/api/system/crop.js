import request from '@/utils/request'

// 查询作物管理列表
export function listCrop(query) {
  return request({
    url: '/system/crop/list',
    method: 'get',
    params: query
  })
}

// 查询作物管理详细
export function getCrop(id) {
  return request({
    url: '/system/crop/' + id,
    method: 'get'
  })
}

// 新增作物管理
export function addCrop(data) {
  return request({
    url: '/system/crop',
    method: 'post',
    data: data
  })
}

// 修改作物管理
export function updateCrop(data) {
  return request({
    url: '/system/crop',
    method: 'put',
    data: data
  })
}

// 删除作物管理
export function delCrop(id) {
  return request({
    url: '/system/crop/' + id,
    method: 'delete'
  })
}
