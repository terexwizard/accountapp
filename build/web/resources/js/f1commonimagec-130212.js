
var blobid;
var blobpart;
var callback;

var iio;

function initiio(){
    
    iio = top.frm_img;
    
}

function iicopen(){
    
//    top.frm_img.iiopen();
    iio.iiopen();
    
}

function iicopenimage(blob, part){
    
    iio.iiopenimage(blob, part);
    
}

function iicopenclear(){
    iio.iiopenclear();
}
 
function iicsave(icallback){
    
    callback       = icallback;
    
    iio.iisave();
    
    
}

function iicsavecb(){
    
    blobid      = iio.blobid;
    blobpart    = iio.blobpart;
    
    isopensave    = iio.isopensave;
    
    
    var cc      = callback+"('"+blobid+"',"+ blobpart+")";
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

initiio();