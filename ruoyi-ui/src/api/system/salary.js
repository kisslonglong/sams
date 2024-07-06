import request from '@/utils/request'

// 查询员工薪资管理列表
export function listSalary(query) {
  return request({
    url: '/system/salary/list',
    method: 'get',
    params: query
  })
}

// 查询员工薪资管理详细
export function getSalary(id) {
  return request({
    url: '/system/salary/' + id,
    method: 'get'
  })
}

// 新增员工薪资管理
export function addSalary(data) {
  return request({
    url: '/system/salary',
    method: 'post',
    data: data
  })
}

// 修改员工薪资管理
export function updateSalary(data) {
  return request({
    url: '/system/salary',
    method: 'put',
    data: data
  })
}

// 删除员工薪资管理
export function delSalary(id) {
  return request({
    url: '/system/salary/' + id,
    method: 'delete'
  })
}
