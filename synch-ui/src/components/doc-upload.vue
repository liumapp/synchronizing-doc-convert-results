/**
 * @file doc-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<template>
  <div>
    <Upload
      multiple
      type="drag"
      action="//jsonplaceholder.typicode.com/posts/">
      <div style="padding: 20px 0">
        <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
        <p>Click or drag files here to upload</p>
      </div>
    </Upload>
  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'doc-upload',

}
</script>
