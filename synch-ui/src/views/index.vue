/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/2/18
 */
<template>
  <div>
    <Row>
      <Col span="18" offset="3">
      <Card>
        <Steps :current="current">
          <Step title="上传doc文档" content="form表单上传一个待转换的doc文档"></Step>
          <Step title="转换过程中" content="通过socket等待转换结果"></Step>
          <Step title="获取转换结果" content="可下载转换好的pdf文档"></Step>
        </Steps>
        <doc-upload v-if="current == 0"></doc-upload>
        <converting v-if="current == 1"></converting>
        <convert-result v-if="current == 2"></convert-result>
      </Card>
      </Col>
    </Row>
  </div>
</template>
<script>
import docUpload from '@/components/doc-upload'
import converting from '@/components/converting'
import convertResult from '@/components/convertResult'
export default {
  name: 'index',
  components: {
    docUpload, converting, convertResult
  },
  data () {
    return {
      current: 0
    };
  }
}
</script>
