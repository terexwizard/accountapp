
var blobid;
var blobpart;
var callback;

var iio;

var currentpage = "";

function initiio(){
    
    iio = top.frm_img;
    
}

function iicopen(){
    
//    top.frm_img.iiopen();

    
    iio.iiopen(currentpage);
    
}

function iicopenimage(blob, part){
    
    iio.iiopenimage(blob, part, currentpage);
    
}

function iicgetimage(blob, part) {
    iio.iigetimage(blob, part);
}

function iicopenclear(){
    iio.iiopenclear();
}
 
function iicsave(icallback){
    
    callback       = icallback;
    
    iio.iisave();
    
    
}

function iicsavecb(iblobid, iblobpart, iopensave){
    
    blobid      = iblobid;
    blobpart    = iblobpart;
    
    isopensave    = iopensave;
    
    //window.status   += " cb :"+blobid+"/"+blobpart;
    
    var cc      = callback+"('"+blobid+"','"+ blobpart+"');";
    //alert(blobid+" / "+blobpart+" // "+cc);
    
    try{
        eval(cc);
    }catch (e){
        //alert(e);
    }
    
}

function iicclose(){
    
    iio.iiclose();
    
}

function iicclear(){
    
    iio.iiclear();
    
}

function iicviewmode(vmode){
    
    iio.iiviewmode(vmode);
    
}

function iicsetmultisave(part) {
 
        iio.iisetmultisave(part);
}

function iicsetmulti(flag){
        iio.iisetmulti(flag);
}

function iicgetproperty(key){
    
    return iio.iigetproperty(key);
    
}

function iicsetproperty(key, value){
    
    iio.iisetproperty(key, value);
    
}

function iicremovetoolbar(name){
    iio.iiremovetoolbar(name);
}

function iicunremovetoolbar(name){
    iio.iiunremovetoolbar(name);
}

function iichideprint(){
    iio.iihideprint();
}

function iicshowprint(){
    iio.iishowprint();
}

function iichidesave(){
    iio.iihidesave();
}

function iicshowsave(){
    iio.iishowsave();
}


function iicinitpage(){
    
    currentpage   = document.location.href;
    
    iio.iiinitpage(currentpage);
}

initiio();