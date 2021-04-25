package com.xmut.eduservice.controller.admin;

import com.xmut.commonutils.R;
import com.xmut.eduservice.model.entity.Chapter;
import com.xmut.eduservice.model.entity.vo.ChapterVo;
import com.xmut.eduservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/admin/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @PostMapping("save")
    public R save(
            @RequestBody Chapter chapter
            ){
        chapter.setGmtModified(new Date());
        chapter.setGmtCreate(new Date());
        boolean result = chapterService.save(chapter);
        if (result){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }

    @GetMapping("get/{id}")
    public R getById(
            @PathVariable String id
    ){
        Chapter chapter = chapterService.getById(id);
        if (chapter != null){
            return R.ok().data("item",chapter);
        }else {
            return R.error().message("数据不存在");
        }
    }

    @PutMapping("update")
    public R updateById(
            @RequestBody Chapter chapter
    ){
        chapter.setGmtModified(new Date());
        boolean result = chapterService.updateById(chapter);
        if (result){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id){
        boolean result = chapterService.removeChapterById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }


    //嵌套
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @PathVariable String courseId
    ){
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items",chapterVoList);

    }
}
