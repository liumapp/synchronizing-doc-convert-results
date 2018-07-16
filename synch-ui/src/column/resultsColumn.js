/**
 * @file resultsColumn.js
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/7/18
 */
import status from '@/libs/status';

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
      let text = '';
      let color = '';
      if (row.status === status.CONVERTED_SUCCESS) {
        text = '成功';
        color = 'green';
      } else if (row.status === status.CONVERTED_FAILD) {
        text = '转换失败';
        color = 'red';
      } else {
        text = '转换中';
        color = 'yellow';
      }
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
    status: status.CONVERTED_SUCCESS
  },
  {
    filename: 'b',
    statue: status.WAITING_CONVERT
  }
];

const resultColumn = {
  tableResultsColumn: tableResultsColumn,
  tableResultsData: tableResultsData
}

export default resultColumn;
