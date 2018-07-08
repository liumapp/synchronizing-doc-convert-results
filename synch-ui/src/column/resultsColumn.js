/**
 * @file resultsColumn.js
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/7/18
 */

export const tableResultsColumn = [
  {
    title: '序号',
    type: 'index',
    align: 'center',
    width: 80
  },
  {
    title: '文档名称',
    align: 'center',
    render: (h, params) => {
      const row = params.row;
      return h('span', row.filename);
    }
  },
  {
    title: '转换状态',
    align: 'center',
    render: (h, params) => {
      const row = params.row;
      const text = row.status === 1 ? '成功' : '转换中';
      const color = row.status === 1 ? 'green' : 'red';
      return h('Tag', {
        props: {
          type: 'dot',
          color: color
        }
      }, text);
    }
  },
  {
    title: '操作',
    align: 'center',
    handle: true
  }
];

export const tableResultsData = [
  {
    filename: 'a',
    status: 1
  },
  {
    filename: 'b',
    statue: 0
  }
];

const resultColumn = {
  tableResultsColumn: tableResultsColumn,
  tableResultsData: tableResultsData
}

export default resultColumn;
