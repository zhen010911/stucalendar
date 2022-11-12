package com.wz.stucalendar.controller;

import com.wz.stucalendar.entity.Calendar;
import com.wz.stucalendar.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
//@EnableScheduling
@RequestMapping("/notes")
public class NotesController {
    private NoteService noteService;
    private int x=0;
    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    //定时提醒
    @GetMapping("/start")
    @Scheduled(cron = "0/3 * * * * ? ")
//    @RequestMapping("start")

    public String scheduled() {
//        //当前时间
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String date1 = simpleDateFormat1.format(new Date());
//        String date2 = simpleDateFormat2.format(new Date());
        //获取数据库里的时间
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/M/d");
        String date1 = simpleDateFormat1.format(new Date());
//        Calendar calendar = noteService.findByName();
        boolean ensure = noteService.EnsureByName(date1);
//        String date3="2022/10/02";
        System.out.println(ensure);
        System.out.println(date1);
        if (ensure){
                System.out.println("执行任务！！！");
                return "refresh";
        }
        else{
                return "index";
        }
    }



    @RequestMapping("display")
    public String display(){
        return "calendar";
    }

    @RequestMapping("save")
    public String save(Calendar calendar) throws UnsupportedEncodingException {
        try {
            noteService.save(calendar);
        }catch (Exception e){
//            return "index.html?msg=" + URLEncoder.encode(e.getMessage(), "UTF-8");
            return "datexisterr";
        }

        return "redirect:/notes/finish";
    }

    @RequestMapping("update")
    public String update(Calendar calendar){
//        try{
//           Boolean notEmpty = noteService.getByName(calendar.getDate());
//           if(notEmpty){
//               noteService.update(calendar);
//           }
//        }catch (Exception e){
//            return "index";
//        }
        noteService.update(calendar);
        return "redirect:/notes/finish";
    }

    @RequestMapping("delete")
    public String  delete(Calendar calendar){
        noteService.delete(calendar);
        return "redirect:/notes/finish";
    }

    @RequestMapping("finish")
    public String finish(Model model){
        model.addAttribute("noteService",noteService);
        return  "redirect:/notes/display";
    }



//    @RequestMapping("detail")
//    public String detail(String date,Model model){
//        Calendar calendar = noteService.findByName(date);
//        model.addAttribute("calendar",calendar);
//        return "redirect:/index";
//    }
//
//    @RequestMapping("search")
//    public String search(Model model,String date){
//        Calendar calendar = noteService.findByName(date);
////        String note = calendar.getNotes();
//        String note = "asdasdsadsdasdsd";
//        model.addAttribute("note",note);
//        return "index::refresh";
//    }

    @RequestMapping("/local")
    public String lists(Model model){
        List<Calendar> calendarList = noteService.lists();
        model.addAttribute("title","备忘录查询");
        model.addAttribute("calendarLists",calendarList);
        return "calendar::table_refresh";
    }

}
