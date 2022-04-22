

--VARIABLES
local jump=false
local cX = display.contentCenterX
local cY = display.contentCenterY
height=display.actualContentHeight
width=display.actualContentWidth
display.HiddenStatusBar=true
display.setDefault( "anchorX", 0 )
display.setDefault( "anchorY", 0 )
local TOP_REF = 0
local BOTTOM_REF = 1
local LEFT_REF = 0
local RIGHT_REF = 1
local CENTER_REF = 0.5
baseline=280
score=0
life=3
level=0
function readScore()
local path = system.pathForFile( "score.dat", system.DocumentsDirectory )
local file = io.open( path, "r" )
if file==nil then
	highScore=0
else
local savedData = file:read( "*a" )
highScore=savedData
io.close( file )
file = nil
end
end

function saveScore()
local saveData = score
local path = system.pathForFile( "score.dat", system.DocumentsDirectory )
local file = io.open( path, "w" )
if file==nil then

	else
file:write( saveData )
io.close( file )
file = nil
end
end
  --ads


---music
local music=audio.loadStream( "music/music.mp3" );audio.setVolume( 0.5, { channel=1 } ) -- set the volume on channel 1
local backgroundMusicChannel = audio.play( music, { channel=1, loops=-1, fadein=3000 } )
local bombSound = audio.loadSound("music/bomb.wav");audio.setVolume( 0.2, { channel=2 } ) -- set the volume on channel 1
local coinSound=audio.loadSound("music/coin.mp3");audio.setVolume( 1,{channel=3, loops=0, fadein=1000})
local manGrunt=audio.loadSound("music/manGrunt.mp3");audio.setVolume( 1,{channel=4, loops=0, fadein=1000})
local levelUp=audio.loadSound( "music/levelUp.wav" )

--IMAGES

--OTHER IMAGES



--SCOREs


--BAR,BoMB and COINS

bgs={}
chooseBg={}
function chooseBg()
	math.randomseed( os.time(  ) )
 n=math.random( 1,6 )
for i=1,3 do
	bgs[i]=display.newImage( "bgs/bg"..n..".png");
	bgs[i].width=480
	bgs[i].height=320
	bgs[1].x=0
	if(i>1) then
	bgs[i].x=bgs[i-1].x+bgs[i-1].width
end
bgs[i].y=0

end

end



function loadScore()
scoreImage=display.newImageRect("img/coin.png",25,25)
scoreImage.x=0
scoreImage.y=30
 scoreText=display.newText(" X "..score.."/"..highScore,15,30,system.nativeFontBold,20);
scoreText:setFillColor(1,0,0 )
lifeScore=display.newImage( "img/life.png",width-80,30);lifeScore:scale(0.3,0.3);
 lifeText=display.newText( life,width-65,35,system.nativeFontBold,20 )
 levelText=display.newText( "LEVEL : "..level,width/2+100,30,system.nativeFontBold,20 )
levelText:setFillColor( 01,1,1)

end 
function loadGrass(  )
	-- body
	 grass = display.newImage( "img/grass.png" )
grass.x = 0
grass.y = 260
 grass2 = display.newImage( "img/grass.png" )
grass2.x = 480
grass2.y = 260
 ground = display.newRect( 0, 300, 480, 90 )
ground:setFillColor( 0x31/255, 0x5a/255, 0x18/255 )


end


function loadItems(  )
	-- body
	bar={}
bomb={}
coins={}
local coin=graphics.newImageSheet( "img/coins.png",{ width=40, height=178/4, numFrames=4 } )
for i=1, 3 do

math.randomseed( os.time() )
coins[i]=display.newSprite(coin,{ name="coin", start=1, count=4, time=300 });
bar[i]=display.newImage("img/bar.png",300,245);bar[i]:scale(0.3,0.2);
bomb[i]=display.newImage( "img/bomb.png",300,245);bomb[i]:scale(0.3,0.3);
bar[i].x=math.random(1,4)*i*math.random(400,600)+i*math.random(width,width+300)
coins[i].x=math.random(2,3)*i*math.random(100,400)+i*math.random(width+30,width+300)
coins[i].y=math.random(200,230);coins[i]:scale(0.8,0.8);
end
love=display.newImage( "img/life.png")
love:scale(0.2,0.2)
love.x=6000
love.y=height-80
--[[
badCoin=love
badCoin.x=1000
badCoin.y=height-100
badCoin:setFillColor(0,0,0)--]]
end
-- solid ground, doesn't need to move

function actorLoad( )
	local frameNum =0
 sequenceData =
{
    { name="walking", start=1, count=30,time=500},
       {name="stop",start=3,count=1,time=600},
 
}
 sheet2 = graphics.newImageSheet( "img/anim.png", { width=512/6, height=512/5, numFrames=30 } )
 actor = display.newSprite( sheet2, sequenceData );actor.x = 100;actor.y = 110;actor:scale(0.6,0.6);
actorStop=display.newSprite( sheet2, {start=5,count=1} );actorStop:scale(0.6,0.6);actorStop.y=actor.y;actorStop.x=actor.x;
actorStop.alpha=0;
actor.y=height
--MAIN LOOP
actor:setFillColor( 0,0,1 )
actorStop:setFillColor( 0,0,1 )
end

readScore();
chooseBg()
loadScore()
loadGrass()
loadItems()
actorLoad()
skyBg=display.newImageRect("img/sky.jpg",display.contentWidth,display.contentHeight)
 	 runner=display.newImage( "img/runner.png",100,100)
 	 	 mount=display.newImage( "img/mountain.png",0,height-60);
math.randomseed( os.time()  )
scoreText.anchorX=0
local tPrevious = system.getTimer()
 --speed=level/2+0.8;

local function move(event)

    speed=level/2+4;
	local tDelta = event.time - tPrevious
	tPrevious = event.time
	local xOffset = ( 0.2 * tDelta )


love.x=love.x-xOffset
	grass.x = grass.x -( xOffset+speed)
	grass2.x = grass2.x -( xOffset+speed)
	
	if (grass.x + grass.contentWidth) < 0 then
		grass:translate( 480 * 2, 0)
	end
	if (grass2.x + grass2.contentWidth) < 0 then
		grass2:translate( 480 * 2, 0)
	end
for i=1,3 do
	
	bgs[i]:translate(-1,0)
	bar[i]:translate(-(xOffset+speed), 0)
	coins[i]:translate(-(xOffset+speed), 0)
	bomb[i].x=bar[i].x
  coins[i]:play()
  
  if(bgs[i].x<-bgs[i].width) then
  	bgs[i].x=width+10
  end


	if ((actor.x+actor.contentWidth)>=bar[i].x and (actor.x+actor.contentWidth)<=(bar[i].x+bar[i].contentWidth) and (actor.y+actor.contentHeight)>=bar[i].y and actor.y<=(bar[i].y+bar[i].contentHeight)) then
audio.play(manGrunt);audio.setVolume( 1, { channel=4 } )
local audio2=audio.play(bombSound);
life=life-1;
	
bar[i].x=-100
	end

if((actor.x+actor.contentWidth)>=love.x and (actor.x+actor.contentWidth)<=(love.x+love.contentWidth)) then
   life=life+1
   love.x=6000
end
		if ((actor.x+actor.contentWidth)>=coins[i].x and (actor.x+actor.contentWidth)<=(coins[i].x+coins[i].contentWidth) and actor.y<=(coins[i].y+coins[i].contentHeight)  ) then
local audio3=audio.play(coinSound)
score=score+100
coins[i].x=-100
coins[i].y=math.random( 180,230 )
	end


	--[[
	if(actor.x<=bar[i].x-100 and actor.x>=bar[i].x+100)  then
   display.newText( "Jump Dude", 200,200,system.nativeFontBold,30)
	end--]]
if(bar[i].x<-100)then 
	math.randomseed( os.time() )
	bar[i].x=math.random(1,3)*math.random(width+100,width+200)
end
if(coins[i].x<-100) then
	coins[i].x=math.random(width+20,width+100)
end
end
if(life<=0) then
actor:setFillColor( 1,0,0 )
actorStop:setFillColor( 1,0,0)
else
	actor:setFillColor( 0,0,1 )
actorStop:setFillColor( 0,0,1)
end
actor:play()
actor.alpha=1
actorStop.alpha=0
actorStop.y=actor.y

if(actor.y<=110) then
	actor.y=110
end
if((actor.y+actor.contentHeight)<=(220)) then
jump=false
end


		if((actor.y)<=(220)) then
			actorStop.alpha=1;
			 actorStop:play()
			 actor.alpha=0;
end
 if(jump==false and (actor.y)<=220) then
  	actor.y=actor.y+7
  	actor:setSequence( "walking" )
   actor:play()

end

if(jump==true) then
	actor.y=actor.y-10
	 print(actor.y)
end 


	score=score+1
scoreText.text="   X "..score.."/"..highScore
lifeText.text=life;

if(life==-1) then
lifeText.text=0
end
level=math.floor(score/1000)
levelText.text="LEVEL : "..level;
levelText.anchorX=width/2;
if(life<0) then
         local hs=tonumber(highScore)
		if(score>hs) then
		saveScore();
	end
	gmover=display.newRoundedRect( width/2-120,50,240,200,20)

	gmover2=display.newText( "GAME OVER\n\n".."SCORE : "..score,width/2-110,70,system.nativeFontBold,25)

	gmover:setFillColor( 0,0.5,0.5 )
	play_again=display.newImage( "menu/play_again.png",width/2-70,200)
Runtime:removeEventListener( "enterFrame",move )
play_again:addEventListener("tap",playAgain)
	end
	
--data.text=actor.y+actor.contentHeight..bar[1].y
end

-- Main Function
function playAgain( )
	gmover:removeSelf( )
	play_again:removeSelf( )
	gmover2:removeSelf( )
	highScore=readScore();
for i=1, 3 do
    bgs[i]:removeSelf() -- Optional Display Object Removal
    bgs[i] = nil  
    bomb[i]:removeSelf( )
    bomb[i]=nil
	bar[i]:removeSelf( )
	bar[i]=nil
	coins[i]:removeSelf( )
	coins[i]=nil    -- Nil Out Table Instance
end
	love.x=8000
	grass:removeSelf( )
	grass2:removeSelf( )
	levelText:removeSelf( )
	lifeText:removeSelf( )
	scoreText:removeSelf( )
	actor:removeSelf( )
	actorStop:removeSelf( )
	life=3
	score=0
	level=0
 chooseBg();
 readScore();
 loadScore()
loadGrass()
loadItems()
actorLoad()
	Runtime:addEventListener( "enterFrame",move )

end
function showTitleScreen(type)


if(type=="main") then
p=100
 title=display.newImage( "menu/title.png",5,12)
 title:scale(0.5,0.5)
	 playBtn=display.newImage( "menu/play.png",width-150,p);
	playBtn:scale( 0.6, 0.6 )
	 scoreBtn=display.newImage( "menu/score.png",width-150,p+150);
	scoreBtn:scale( 0.4, 0.6 )
	 helpBtn=display.newImage( "menu/help.png",width-150,p+100);
	helpBtn:scale( 0.6, 0.6 )
	 aboutBtn=display.newImage( "menu/about.png",width-150,p+50);
	aboutBtn:scale( 0.6, 0.6 )
	playBtn:addEventListener( "tap",play )
   scoreBtn:addEventListener( "tap",scoreMenu )
     helpBtn:addEventListener( "tap",helpMenu)
      aboutBtn:addEventListener( "tap",aboutMenu)
   	titleView = display.newGroup(title,playBtn, scoreBtn, helpBtn,aboutBtn)
 elseif type=="score" then
scaletoZero();
scoreView=display.newText( "HighScore : "..highScore ,100,100,system.nativeFontBold,30)
scoreView:addEventListener('tap', hideScore)
elseif type=="help" then
scaletoZero();
  local helpString="How To Play ?:\n1.Tap the Screen to Jump \n2.Avoid touching the Bombs\n3.Jump before your leg touches the bomb\n4. Try to break the high score\n5. Share the Game If you liked it\n6.Ads will Shown up if you touch the bomb\nTouch Here to go back"
help= display.newText( helpString,10,70,system.nativeFontBold,20  )
help:addEventListener('tap', hideHelp)
elseif type=="about" then
	scaletoZero();
local path = system.pathForFile( "credits.txt")
local file = io.open( path, "r" )
if file==nil then
	credits=0
else
local data = file:read( "*a" )
credits=data
io.close( file )
file = nil
end

 about= display.newText(credits,10,30,system.nativeFontBold,9  )
 about:setFillColor(0,0,0)
about:addEventListener( 'tap', hideAbout )
end
     end       
function scaletoZero()
playBtn:scale(0,0)
scoreBtn:scale(0,0)
helpBtn:scale(0,0)
aboutBtn:scale(0,0)
title:scale(0,0)

end
function hideScore( )
display.remove(scoreView)
showTitleScreen("main")
end
function hideAbout( )
	
	transition.fadeOut( about,  {time = 00, x = 0} )
	display.remove(about)
showTitleScreen("main")
end
function hideHelp( )
display.remove(help)
showTitleScreen("main")
end
function helpMenu()
	showTitleScreen("help")
	end
function scoreMenu( )
showTitleScreen("score")
end

function aboutMenu(event)
	
showTitleScreen("about")
	end
function play(event)
score=0;
life=3;
display.remove( skyBg )
display.remove(runner)
display.remove(mount)
display.remove(titleView)
Runtime:addEventListener( "enterFrame", move );
display.getCurrentStage():addEventListener("tap", manJump );
end 
--MAN JUMPS 
 function manJump(event)
 
		jump=true

	return true
end




--CALLING FUNCTIONS 
function Main()
	showTitleScreen("main");
	
end

Main();
