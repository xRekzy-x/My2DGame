package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ImageModification;
import main.UI;

public class Entity {
    GamePanel gp;
    private UI ui;
    public int x,y;
    
    public int speed;
    
    public BufferedImage up1,up2,up3,up4,up5,up6,
                        down1,down2,down3,down4,down5,down6,
                        right1,right2,right3,right4,right5,right6,
                        left1,left2,left3,left4,left5,left6,
                        sdown1,sdown2,sdown3,sdown4,sdown5,sdown6,
                        sup1,sup2,sup3,sup4,sup5,sup6,
                        sright1,sright2,sright3,sright4,sright5,sright6,
                        sleft1,sleft2,sleft3,sleft4,sleft5,sleft6;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    private Rectangle solidArea = new Rectangle(24,32,64,64);
    public int solidAreaDefaultX,solidAreaDefaultY,triggerInteractDefaultX,triggerInteractDefaultY;
    private int dialogueIndex=0;
    private Rectangle triggleInteract = new Rectangle(-50,0,192,192);


    protected boolean collisionOn = false;
    public Entity(GamePanel gp){
        this.gp=gp;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
        this.triggerInteractDefaultX = triggleInteract.x;
        this.triggerInteractDefaultY = triggleInteract.y;
        ui = new UI(gp);
    }
    public Rectangle getSolidArea(){
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea){
        this.solidArea=solidArea;
    }
    public boolean getCollisionOn(){
        return collisionOn;
    }
    public void setCollisionOn(boolean collisionOn){
        this.collisionOn=collisionOn;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getSolidAreaDefaultX(){ return solidAreaDefaultX; }
    public int getSolidAreaDefaultY(){ return solidAreaDefaultY; }
    public int getSolidAreaX(){return solidArea.x;}
    public int getSolidAreaY(){return solidArea.y;}
    public int getSolidAreaWidth(){return solidArea.width;}
    public int getSolidAreaHeight(){return solidArea.height;}
    public int getTriggerInteractX(){return triggleInteract.x;}
    public int getTriggerInteractY(){return triggleInteract.y;}
    public int getTriggerInteractWidth(){return triggleInteract.width;}
    public int getTriggerInteractHeight(){return triggleInteract.height;}
    public int getTriggerInteractDefaultX(){return triggerInteractDefaultX;}
    public int getTriggerInteractDefaultY(){return triggerInteractDefaultY;}
    public Rectangle getTriggerInteract() {return triggleInteract;}
    public int getSpeed(){return speed;}
    public UI getui(){return ui;}

    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setSolidAreaDefaultX(int solidAreaDefaultX){ this.solidAreaDefaultX=solidAreaDefaultX;}
    public void setSolidAreaDefaultY(int solidAreaDefaultY){ this.solidAreaDefaultY=solidAreaDefaultY;}
    public void setSolidAreaX(int solidAreaX) {this.solidArea.x=solidAreaX; }
    public void setSolidAreaY(int solidAreaY) {this.solidArea.y=solidAreaY; }
    public void setTriggerInteractX(int triggerInteractX ){this.triggleInteract.x=triggerInteractX;}
    public void setTriggerInteractY(int triggerInteractY ){this.triggleInteract.y=triggerInteractY;}
    public void setTriggerInteractWidth(int triggerInteractWidth ){this.triggleInteract.x=triggerInteractWidth;}
    public void setTriggerInteractHeight(int triggerInteractHeight ){this.triggleInteract.x=triggerInteractHeight;}
    public void setui(UI ui){this.ui=ui;}

    public int getDialogueIndex() { return dialogueIndex; }
    public void setAction(){}
    public void speak(){
        if(gp.getui().getDialogue(dialogueIndex)==null) dialogueIndex=0;
   _�wJ��fBj���l�x�;��B�L�Q;���pW@���7>1ڔ�kٌA��*a�R�
�ku�&4�pɺܢmǬ�,c�{�bQ�T4����yOê��̀��7ր�=���� �����x� )(M�^OA�>LdU5At�j|7�A�g9ك���#�@�n�]�w�^������(P<8G��-��z��~8�`�~��J�ǘ���P�|f
b$��i3y�е� UpD[� 5�-hQ�_�L3M!�g�`�y�(����X柵)�7�	����M�Hb6c?c�D���tP�|� n��7�hx1����L�3��8}_ �[�?>�b�Q��� e��x�Q�p4B�:���	�f�7�>/�j�}H��U {w�D/����]�>_������SdkyBRi�֪���e�I�6I�=ۢ��4Q�����v��C��Q�B��� ���N7?�����B�z�D6�*�$X݅U����-_|�7|5%+3����ǧ~�6��N��(O;�rBI�;�-��Hr�(�: k8C�5��NP�C@\��uL6a�]F�z�/c2�nT1y�ϴG@�֙�a=^ȕ�_��kn��/�f�hg�d'ظh 6��>�Ce�-����R鍂:�R4H|������W��^�Ȱ�s(6�%�N[&X�\3u G�G�G�T�X��[��^BX�yf<��03�!KB�nD�h���ųOJ����6��0Rq Ż��gj5f 	��g�j>��tx/wDܭf*#b,ZN:�*utd5S���6D�����)6f��@�ĺg��̣�-_
�^����Y$n��ެ`��Zo��uś�ޤ���ܶ��*���u}~��yԧ0�:�|�Ň$�e�X ����j�
���:�*�CA�n�c����=���.���4��mŌA&���r9�y�S�4�S��S��9�c8�fѐ�/ C_�̮����ҥI�?�~B�n^��q,]W$�)|L����b�,l8W���An&��k���q��j��������"ObY��3��:�t��۽���Aɳ�j{�l�L(��wh�����ZXO�t�[x�x�^&�@�*����"�	:Rt�`� �~���8�!s�?z{��hك��T��9�f��vJ�3OaS�;�Gˇ�!ЂdT��gb@3�ꘪ������T�����M6�w��
TW�b^ Ȧ�C� j��M�/��0�\�xo;��(���j�����WId��fC�ȧ��/�K�侜��6I�`9�A[Ǥ�0�����,�>����2�96�U<"ϣ��h�?�S>��)CF#X�IIF�c0>*��(�2wu�n]9�D��9���i���@D4)���[�{����Bŧ�kn��|#����<�&^�1��eUs��i����Y�Qa���)CKju�K��F�n IO�U��Ruv;���i���00�������K�'k�m��ݕ����8P��,�l+7t-m0�x���ل��q��:|���� ~�u|%z~+.�#sj���G����Z���F�JB�n�rrR	ŋn�}�'����3Y�}Yl<���C~�G�v6�Iя�EFc��R��M�te����Q�ɖ��	�m&a�[��b�Z���%�c�����b1�v M����M8�'R��,�EѫV������7B�q��g�H��%S��m^b^mV�x
mz�b�X�q�3�+��h�h�[շ8l�9�r
�%�����@X�^���׫O��ZL��p5b�3$r1�ٴ�H�uE@�`2)Maz�e5+�P���������� =:=��-Q �ۍ���$���9H�<�5��	���?ܖ�2&v����ܹDB��l��Cc�y�P0x�p�36���x�����:=��W)��\�3��.��B�ȥ�p�{��h��t ͝p�b4��_S�i��h��QR�(��b+�St���~tD�%���){���������?���g0xe�Z�$֐/F���tV>�	��� ��ј��	ŖF�Z�f�=�T��+����D�70����X*,h��O��UA�<i�<mC���Xq3$�!C���h����.�݈,��r������/�>+� � O��-��{�,ft��p���Z�a|�s̜j/�Q��	�Mb	$%5����^��_I2��{��5K����7��b�R�����	xH��]6�:j<!P�
�F$E�^v��p�bM��Jj�F�d�S$~�>�7��3_�0�={������>qؒFI`T�{�Ù/�y�2@}=�o���d��`\:��ܭ��D7�c ���|��:���$������[�b$�u��YPKQQ>9�m�e-�#�i"���v�Sb�]U �X�7S��q��B�;R���me��2f�fs����ϱ�T#��M}���},��z��:;�l����j�L�E���.c&�t�o�����뙼���u��:���k=Z�F���CaTv)x�A���69��̎B@Q&�S]�z������cQ:�L-����@�a����,���c�:���N��m�'��f�u@��xOaO1I\�rX�U�f)'$9�d��i��F�ЂS��:���)����=z��tI��a�L�0�e�a2�t����8j��o��U&���4-0�}�}M��Xæ���3̀�pjJ��V���o�qa�Q�J5��,f�ab��lv|g��n�t��&�0�~7�ꗚ��RԖ6[o@��D�n�p�f����i�@D�pM<�*���қ��6�H�]!��<��9[*��}O�:Az1���P)θ(�݃Ȫ9x�}�͚q~ ���I���S�H*i��^5�}�^��io2u�J�\݄~8 g9B�r��kZ�ɀ>�9��,`i����+�
�����C<���g�Am�)pWb�E�r�S̉�?�f!ݐ+��/����(��6���δ�q���V.�ȋT��@y'J{�?�O�f�
���%�7�]��9h{N
�w�˶}\6��ϐ�?K��B��B&�,fƸ�����A�D�^V3��1�(���;hG��ý ����zK���r����[t�ڝ�&yg~Ę?ަ��Ye��:C�x~�UP�eӎ�
�����#��*6%�^�1� �p�|s��B'S�7�-��K��ɸ�V�Z.�F���*:l�$Y���y?瑍�Y��_�m5{RG5���,�����t�O�͓-��4����r|���CI�+R1�,(.�m�ne���
�XE]���2]&�.��A-��ؖ���n�,�Nv��'K[=|YF�YZ-W��}�]/�SY�Udb,�HT����,�A��7U�� ;!\��?�^C�;�|'������0������ҭ�M?�X��M�k���R�<dF�O.c9������������6�+�,��Ű��j�)z����u�%���L�-F���=K�:�I�2�'�M�/.�iRV 
�b4�������C0���hd�7����6E�|��P�u����Fء���:���-��1�?�,�u��$��d5E*���]d2���q�-k��]Q���<"CY��u6��Ϩ�0D�Uݹ`?u��s���d+��DK=�1����$�� m��&���7l*�m��'�筕�}��oEÊ/I~	����Wa�>��G��ڪ����h������H0j$�qC�P̅h��D4C�.O#O���$WpNx��SP��˞.XbȬq1V�p2S�Q�x���n2�9��g̼�X��`��o@��_i�D��bI#�=�X�7:?w�2@�r�A���Ӯ�;�7��	���]���9{�+��l��i��	����N-ؖ;K��_"�K	�����,�5�n�z���_�S�������t@*_Fq��&Ͽ=�^:�9kJ�S��\�� 0�}˚��i���͞��'aA͎q��^l��P���6�6NJ	�����~?�`��V�T�+��Է�|q� r_�`��H�@��;�lG��S�E~׌4�˅K���[�'�<%��@Sh',� ���u�%y�Ī�A?ǲ�P��$	�Ӽ��Dm��y�3�u	Y^���*��I_ʣ�՛��
$�?�C��YP�!������8$���y�N�>�Ƹ��eyٹ�8���7�H�t��Y�r�-�Fzk��_c1/O�K�!<g�ݩeh�и�`Ź;��ǥ����s):j:u��yd˔h�VU�aڇ�(�d�נ��F l��~��`���>�jJ�F?��r��:�ˁFD���y䤊�^m��|z���ȫ_0���1�Q�����-�J���ݲ����͜�
B_���O�u<�gk�����'h���֮)����������4{Ι<[��5�l���d�ÿ7��߭(�����q�ӣ	\E0-S�/�]�(�>u��v� ���L���s&��H�%"�x!��#��)v��	n�B����H���N�=��h���i+`8љD�`�Z��&��jS�vx�h���3{�s��_��QkV��kh�i�
��t���RY���:��TF��!��W�6V���}l��N�GG�z]]F�:�M�:T�b�p����u�]����[	Ti(�f�����ڰ��3<v�5�1N@�� &�o}T�ʦ���:Z�=��}���6����Þ�u՚i��Z�)��n��
aIץvЯ��/��EpZܟ����Jm�؛�BO����U���'s�~�:\O)d�9���k�U�"�őm��4��`��M8�]�p��t:_xpa*BJ�o�3ʸF"d�$���t�V5AW��Kg`�o�s�k�x?��*�����`�A���5�V����M2�4����Bq��\���Ce�R
^� �&K�q��.Ѳ�)jmA  =�C{W�N�o��ll)�����&!;���1�ƀ�����$�AIԾ4��Qg\A���d� Q@Wfm>A���q���"܂�|^�b#��܄B�Űh�dtå����	�;��]��=0gU4Px;�p�ґN��}�����aL�?Q��Ƈz�S�ma�@�sJx�XA��I�9R���A�9���8������� �{$i'7��9'4�#$P�H��(�f�Ț{�[xFyZ��u�ӟLrQ��΃���+wjp݀�bX�rS4H��J�-N��Siլ�a|D�r��o]ge�4���.TB��y��D(� /.�s��D��OB\�o�#upxҸ�r��@H?���,\*��i��KJ��L^	$��L �ΕSj{T
~s���|g��&:�C�z�ϔ6&��t"��������Ҟ�?��[������/��2�#���}�����|t�߆m\�QE�CI�Eu�@U��R�2^ދRP��z�@OǱ)�̘��NCn�a����C��V"�f�qܹ�ӯ.�{��%,���+یq�U)5ﰗ����J@��6tSxQl�7��^k�i���۾�0B�@c�(}�����O�~��.�� |���0�|aE�#